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
./gradlew runClient    # Inicia o cliente
./gradlew runServer    # Inicia o servidor
```

**Nota:** Para debugar via terminal, você precisará usar a integração Gradle do seu editor ou configurar um debugger manualmente.

---

## Boas Práticas de Desenvolvimento

### 1. Nomeação de Arquivos e Referências

Mantenha um padrão consistente para nomes de arquivos de items:

- **Padrão:** `[item_name]_[estado]`
  - Exemplos: `etherite_raw.json`, `etherite_fragment.png`, `shalecite_fragment.json`
  - O nome do item vem **primeiro**, seguido do seu possível estado (raw, fragment, polished, etc.)

### 2. Estrutura de Diretórios

Organize os assets e código-fonte de forma lógica:

```
src/
├── main/
│   ├── java/net/salsicraft/salsicraft/
│   │   ├── item/          # Definições de items
│   │   ├── block/         # Definições de blocos
│   │   └── util/          # Utilitários
│   └── resources/
│       └── assets/salsicraft/
│           ├── models/item/    # Modelos JSON de items
│           ├── textures/item/  # Texturas PNG de items
│           └── lang/           # Arquivos de tradução
```

### 3. Registro de Items

Mantenha todos os items em um arquivo centralizado (`SalsicraftItems.java`):

- Use constantes com nomes em **UPPER_SNAKE_CASE**
- Registre os items com nomes em **lowercase com underscore**
- Exemplo: `public static final Item ETHERITE_RAW = registerItem("etherite_raw", ...)`

### 4. Arquivos de Modelo (JSON)

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

### 5. Tradução (en_us.json)

Mantenha as chaves de tradução consistentes com os nomes dos items:

- **Padrão de chave:** `item.salsicraft.[item_name]_[estado]`
- **Exemplo:** `"item.salsicraft.etherite_raw": "Raw Etherite"`

### 6. Checklist para Novo Item

Ao adicionar um novo item, certifique-se de:

- [ ] Registrar a constante em `SalsicraftItems.java`
- [ ] Criar arquivo de modelo (`models/item/[item_name]_[estado].json`)
- [ ] Adicionar textura (`textures/item/[item_name]_[estado].png`)
- [ ] Adicionar tradução em `lang/en_us.json`
- [ ] Testar visualmente no jogo com `./gradlew runClient`

---

Para mais informações, consulte a [documentação oficial do Fabric](https://docs.fabricmc.net/1.21.1/develop/getting-started/).
