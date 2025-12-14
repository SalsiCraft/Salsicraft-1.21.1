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

## Datagen - Geração Automática de Dados

O projeto utiliza o **Fabric Data Generation API** para gerar automaticamente arquivos JSON de configuração, evitando a criação manual e reduzindo erros.

### O que é Datagen?

Datagen é uma ferramenta que gera automaticamente arquivos de dados do Minecraft (JSON) a partir de código Java. Isso inclui:

- **Modelos de itens e blocos** - Arquivos JSON em `models/item/` e `models/block/`
- **Block states** - Arquivos de estado de blocos em `blockstates/`
- **Loot tables** - Tabelas de drop de blocos em `data/salsicraft/loot_table/blocks/`
- **Tags** - Tags de mineração e ferramentas necessárias em `data/minecraft/tags/`
- **Receitas** - Receitas de crafting e smelting (quando configurado)

### Por que usar Datagen?

✅ **Consistência**: Reduz erros de digitação e formatação  
✅ **Produtividade**: Gera múltiplos arquivos automaticamente  
✅ **Manutenção**: Mudanças em código refletem automaticamente nos JSONs  
✅ **Validação**: Erros são detectados em tempo de compilação  
✅ **Versionamento**: Mais fácil fazer merges e revisar mudanças

### Como Usar o Datagen

Para gerar todos os arquivos de dados do mod, execute:

```bash
./gradlew runDatagen
```

Os arquivos gerados aparecerão em `src/main/generated/` e devem ser commitados no repositório.

### Estrutura do Datagen

A configuração do datagen está em `src/client/java/net/salsicraft/`:

```
client/
├── SalsicraftDataGenerator.java       # Ponto de entrada do datagen
└── datagen/
    ├── SalsicraftModelProvider.java       # Gera modelos de itens/blocos
    ├── SalsicraftBlockTagProvider.java    # Gera tags de blocos (mineração)
    ├── SalsicraftLootTableProvider.java   # Gera loot tables (drops)
    ├── SalsicraftItemTagProvider.java     # Gera tags de itens
    └── SalsicraftRecipeProvider.java      # Gera receitas (crafting/smelting)
```

### Adicionando Novos Itens/Blocos ao Datagen

Quando criar um novo item ou bloco, adicione-o aos providers correspondentes:

**1. Modelos (SalsicraftModelProvider.java):**
```java
// Para itens simples
itemModelGenerator.register(SeuModulo.SEU_ITEM, Models.GENERATED);

// Para blocos simples
blockStateModelGenerator.registerSimpleCubeAll(SeuModulo.SEU_BLOCO);
```

**2. Tags de Blocos (SalsicraftBlockTagProvider.java):**
```java
getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
    .add(SeuModulo.SEU_BLOCO);

getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
    .add(SeuModulo.SEU_BLOCO);
```

**3. Loot Tables (SalsicraftLootTableProvider.java):**
```java
// Bloco que dropa ele mesmo
addDrop(SeuModulo.SEU_BLOCO);

// Minério que dropa fragmentos (1-3 itens)
addDrop(SeuModulo.SEU_MINERIO, multipleOreDrops(SeuModulo.SEU_MINERIO, SeuModulo.SEU_FRAGMENTO, 1, 3));
```

Após adicionar, execute `./gradlew runDatagen` para gerar os arquivos.

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

### ItemGroup Customizado

O projeto possui uma aba customizada no menu criativo chamada **Salsicraft**, onde todos os itens e blocos do mod aparecem organizados.

**Localização:** `SalsicraftItemGroup.java`

```java
public class SalsicraftItemGroup {
    // RegistryKey usado para auto-registro por módulos
    public static final RegistryKey<ItemGroup> SALSICRAFT_KEY = RegistryKey.of(
        RegistryKeys.ITEM_GROUP,
        Identifier.of(Salsicraft.MOD_ID, "salsicraft")
    );
    
    // ItemGroup registrado (vazio inicialmente)
    public static final ItemGroup SALSICRAFT_GROUP = Registry.register(
        Registries.ITEM_GROUP,
        SALSICRAFT_KEY,
        FabricItemGroup.builder()
            .icon(() -> new ItemStack(Items.DIAMOND))
            .displayName(Text.translatable("itemGroup.salsicraft"))
            .build()
    );
}
```

**Arquitetura Descentralizada:**
- O ItemGroup é criado **vazio** no `SalsicraftItemGroup`
- Cada módulo se **auto-registra** usando `ItemGroupEvents.modifyEntriesEvent()`
- Evita conflitos de merge - cada desenvolvedor edita apenas seu módulo

### Como Adicionar um Novo Minério

**Passo 1:** Crie uma nova pasta em `ores/`
```
ores/
└── crimsonite/
```

**Passo 2:** Crie as classes de registro com auto-registro no ItemGroup

```java
// CrimsoniteOre.java
package net.salsicraft.ores.crimsonite;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.salsicraft.Salsicraft;
import net.salsicraft.SalsicraftItemGroup;

public class CrimsoniteOre {
    public static final Block CRIMSONITE_BLOCK = register("crimsonite_block", ...);
    public static final Block CRIMSONITE_ORE_BLOCK = register("crimsonite_ore_block", ...);
    
    private static Block register(String name, Block block) {
        // Registra o bloco
        Block registeredBlock = Registry.register(
            Registries.BLOCK, 
            Identifier.of(Salsicraft.MOD_ID, name), 
            block
        );
        
        // Registra o BlockItem correspondente
        Registry.register(
            Registries.ITEM, 
            Identifier.of(Salsicraft.MOD_ID, name),
            new BlockItem(registeredBlock, new Item.Settings())
        );
        
        return registeredBlock;
    }
    
    public static void initialize() {
        Salsicraft.LOGGER.info("Registrando blocos de Crimsonite");
        
        // AUTO-REGISTRO no ItemGroup customizado
        ItemGroupEvents.modifyEntriesEvent(SalsicraftItemGroup.SALSICRAFT_KEY).register(entries -> {
            entries.add(CRIMSONITE_BLOCK);
            entries.add(CRIMSONITE_ORE_BLOCK);
        });
    }
}
```

```java
// CrimsoniteItems.java
package net.salsicraft.ores.crimsonite;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.salsicraft.SalsicraftItemGroup;

public class CrimsoniteItems {
    public static final Item CRIMSONITE_ORE = register("crimsonite_ore", ...);
    public static final Item CRIMSONITE_FRAGMENT = register("crimsonite_fragment", ...);
    
    private static Item register(String name, Item item) {
        return Registry.register(
            Registries.ITEM, 
            Identifier.of(Salsicraft.MOD_ID, name), 
            item
        );
    }
    
    public static void initialize() {
        Salsicraft.LOGGER.info("Registrando itens de Crimsonite");
        
        // AUTO-REGISTRO no ItemGroup customizado
        ItemGroupEvents.modifyEntriesEvent(SalsicraftItemGroup.SALSICRAFT_KEY).register(entries -> {
            entries.add(CRIMSONITE_ORE);
            entries.add(CRIMSONITE_FRAGMENT);
        });
    }
}
```

**Passo 3:** Registre no `SalsicraftRegistry.java`
```java
public static void initialize() {
    Salsicraft.LOGGER.info("Inicializando registros do Salsicraft");
    
    SalsicraftItemGroup.initialize();  // Registra o ItemGroup primeiro
    
    // ... outros registros
    
    CrimsoniteItems.initialize();
    CrimsoniteOre.initialize();
}
```

### Vantagens da Arquitetura

✅ **Zero conflitos de merge**: Cada desenvolvedor trabalha em sua pasta de minério  
✅ **Auto-registro descentralizado**: Módulos registram seus próprios itens no ItemGroup  
✅ **Modular**: Fácil adicionar novos conteúdos sem modificar código existente  
✅ **Organizado**: Código relacionado fica junto (blocos + itens do mesmo minério)  
✅ **Escalável**: Suporta crescimento do mod sem refatorações complexas  
✅ **Aba customizada**: Todos os itens/blocos aparecem na aba "Salsicraft" no menu criativo

### Boas Práticas de Desenvolvimento

**Para trabalho em equipe:**
- Cada desenvolvedor cria sua própria pasta de módulo
- Apenas uma linha é adicionada no `SalsicraftRegistry.java` (mínimo conflito)
- Use ordem alfabética no registry para facilitar merges
- Commit frequente de módulos completos
- Cada módulo se auto-registra no ItemGroup - **sem editar classes centrais**

**Para novos tipos de conteúdo:**
- Crie novos módulos fora de `ores/` (ex: `machines/`, `farming/`)
- Mantenha a mesma estrutura de auto-registro
- Use `ItemGroupEvents.modifyEntriesEvent(SalsicraftItemGroup.SALSICRAFT_KEY)` para adicionar ao grupo
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

- [ ] Criar pasta do módulo em `ores/` (se for novo módulo)
- [ ] Adicionar constante na classe do módulo apropriado (`*Items.java` ou `*Ore.java`)
- [ ] Adicionar auto-registro no método `initialize()` usando `ItemGroupEvents.modifyEntriesEvent(SalsicraftItemGroup.SALSICRAFT_KEY)`
- [ ] **Configurar datagen:**
  - [ ] Adicionar ao `SalsicraftModelProvider` (modelos de item/bloco)
  - [ ] Adicionar ao `SalsicraftBlockTagProvider` (tags de mineração, se for bloco)
  - [ ] Adicionar ao `SalsicraftLootTableProvider` (drops, se for bloco)
- [ ] **Executar datagen:** `./gradlew runDatagen`
- [ ] Adicionar textura (`textures/item/[nome].png` ou `textures/block/[nome].png`)
- [ ] Adicionar tradução em `lang/en_us.json`
- [ ] Registrar módulo em `SalsicraftRegistry.java` (se for novo módulo)
- [ ] Testar visualmente no jogo com `./gradlew runClient`
- [ ] Verificar se o item aparece na aba "Salsicraft" do menu criativo

---

Para mais informações, consulte a [documentação oficial do Fabric](https://docs.fabricmc.net/1.21.1/develop/getting-started/).
