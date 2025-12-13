# Como Rodar o Minecraft com Fabric

## Rodando pelo IntelliJ IDEA

O Fabric Loom já vem com perfis de execução prontos. No canto superior direito do IntelliJ, você encontrará dois perfis principais:
<img width="362" height="195" alt="image" src="https://github.com/user-attachments/assets/08712f58-b745-4eb4-b863-2f0858a98582" />


- **Minecraft Client** - Inicia o jogo em modo cliente
- **Minecraft Server** - Inicia o jogo em modo servidor

Ambos podem ser executados em modo normal ou debug. Para debugar, basta selecionar o perfil desejado e clicar no ícone de debug.


**Nota**: Rodar dentro do gradle o `genSources` antes de rodar o jogo.


## Rodando pelo Terminal (VSCode ou outros)

Use os seguintes comandos Gradle:

```bash
./gradlew runClient
./gradlew runServer
```

**Nota:** Para debugar via terminal, você precisará usar a integração Gradle do seu editor ou configurar um debugger manualmente.

---

## Boas Práticas de Desenvolvimento

### 1. Nomeação de Arquivos e Referências

Mantenha um padrão consistente para nomes de arquivos de items:

- **Padrão:** `[item_name]_[estado]`
  - Exemplos: `etherite_ore.json`, `etherite_fragment.png`, `shalecite_fragment.json`
  - O nome do item vem **primeiro**, seguido do seu possível estado (ore, fragment, polished, etc.)

### 2. Estrutura de Diretórios

Organize os assets e código-fonte de forma lógica:

```
src/
├── main/
│   ├── java/net/salsicraft/
│   │   ├── ores/          # Módulos de minérios
│   │   │   ├── etherite/
│   │   │   └── shalecite/
│   │   ├── Salsicraft.java
│   │   └── SalsicraftRegistry.java
│   └── resources/
│       └── assets/salsicraft/
│           ├── models/item/    # Modelos JSON de items
│           ├── textures/item/  # Texturas PNG de items
│           └── lang/           # Arquivos de tradução
```

---

## Arquitetura Modular do Projeto

O projeto utiliza uma arquitetura modular baseada em **auto-registro** para minimizar conflitos durante desenvolvimento colaborativo.

### Estrutura de Módulos

Cada funcionalidade está organizada em módulos independentes:

```
net/salsicraft/
├── ores/                    # Módulo de minérios
│   ├── etherite/
│   │   ├── EtheriteOre.java      # Auto-registro de blocos
│   │   └── EtheriteItems.java    # Auto-registro de itens
│   └── shalecite/
│       ├── ShaleciteOre.java
│       └── ShaleciteItems.java
├── Salsicraft.java          # Ponto de entrada do mod
└── SalsicraftRegistry.java  # Coordenador central
```

### Como Adicionar um Novo Minério

**Passo 1:** Crie uma nova pasta em `ores/`
```
ores/
└── crimsonite/
```

**Passo 2:** Crie as classes de registro
```java
// CrimsoniteOre.java
package net.salsicraft.ores.crimsonite;

import net.minecraft.block.Block;
import net.salsicraft.Salsicraft;

public class CrimsoniteOre {
    public static final Block CRIMSONITE_BLOCK = register("crimsonite_block", ...);
    
    private static Block register(String name, Block block) {
        // Lógica de registro
    }
    
    public static void initialize() {
        Salsicraft.LOGGER.info("Registrando blocos de Crimsonite");
        // Adicionar aos grupos criativos
    }
}
```

```java
// CrimsoniteItems.java
package net.salsicraft.ores.crimsonite;

import net.minecraft.item.Item;

public class CrimsoniteItems {
    public static final Item CRIMSONITE_ORE = register("crimsonite_ore", ...);
    
    private static Item register(String name, Item item) {
        // Lógica de registro
    }
    
    public static void initialize() {
        // Adicionar aos grupos criativos
    }
}
```

**Passo 3:** Registre no `SalsicraftRegistry.java`
```java
public static void initialize() {
    // ... outros registros
    
    CrimsoniteItems.initialize();
    CrimsoniteOre.initialize();
}
```

### Vantagens da Arquitetura

✅ **Zero conflitos de merge**: Cada desenvolvedor trabalha em sua pasta de minério  
✅ **Auto-registro**: Blocos/itens se registram automaticamente quando a classe carrega  
✅ **Modular**: Fácil adicionar novos conteúdos sem modificar código existente  
✅ **Organizado**: Código relacionado fica junto (blocos + itens do mesmo minério)  
✅ **Escalável**: Suporta crescimento do mod sem refatorações complexas

### Boas Práticas de Desenvolvimento

**Para trabalho em equipe:**
- Cada desenvolvedor cria sua própria pasta de módulo
- Apenas uma linha é adicionada no `SalsicraftRegistry.java` (mínimo conflito)
- Use ordem alfabética no registry para facilitar merges
- Commit frequente de módulos completos

**Para novos tipos de conteúdo:**
- Crie novos módulos fora de `ores/` (ex: `machines/`, `farming/`)
- Mantenha a mesma estrutura de auto-registro
- Adicione no `SalsicraftRegistry.java` com comentários claros

---

### Registro de Items/Blocos

Mantenha todos os items/blocos dentro de suas respectivas classes de módulo:

- Use constantes com nomes em **UPPER_SNAKE_CASE**
- Registre com nomes em **lowercase com underscore**
- Exemplo: `public static final Item ETHERITE_ORE = register("etherite_ore", ...)`

### Arquivos de Modelo (JSON)

Estrutura padrão para arquivos de modelo:

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "salsicraft:item/[item_name]_[estado]"
  }
}
```

- A referência de textura deve corresponder ao nome do arquivo PNG
- Use caminhos relativos com o namespace do mod (`salsicraft:`)

### Arquivos de Modelo (JSON)

Estrutura padrão para arquivos de modelo:

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "salsicraft:item/[item_name]_[estado]"
  }
}
```

- A referência de textura deve corresponder ao nome do arquivo PNG
- Use caminhos relativos com o namespace do mod (`salsicraft:`)

### Tradução (en_us.json)

Mantenha as chaves de tradução consistentes com os nomes dos items:

- **Padrão de chave:** `item.salsicraft.[item_name]_[estado]`
- **Exemplo:** `"item.salsicraft.etherite_ore": "Etherite Ore"`

### Checklist para Novo Item/Bloco

Ao adicionar um novo item ou bloco:

- [ ] Adicionar constante na classe do módulo apropriado
- [ ] Criar arquivo de modelo (`models/item/[nome].json` ou `models/block/[nome].json`)
- [ ] Adicionar textura (`textures/item/[nome].png` ou `textures/block/[nome].png`)
- [ ] Adicionar tradução em `lang/en_us.json`
- [ ] Registrar módulo em `SalsicraftRegistry.java` (se for novo módulo)
- [ ] Testar visualmente no jogo com `./gradlew runClient`

---

Para mais informações, consulte a [documentação oficial do Fabric](https://docs.fabricmc.net/1.21.1/develop/getting-started/).
