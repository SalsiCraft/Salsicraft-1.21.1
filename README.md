# Como Rodar o Minecraft com Fabric

## Rodando pelo IntelliJ IDEA

O Fabric Loom já vem com perfis de execução prontos. No canto superior direito do IntelliJ, você encontrará dois perfis principais:
<img width="362" height="195" alt="image" src="https://github.com/user-attachments/assets/08712f58-b745-4eb4-b863-2f0858a98582" />


- **Minecraft Client** - Inicia o jogo em modo cliente
- **Minecraft Server** - Inicia o jogo em modo servidor

Ambos podem ser executados em modo normal ou debug. Para debugar, basta selecionar o perfil desejado e clicar no ícone de debug.

## Rodando pelo Terminal (VSCode ou outros)

Use os seguintes comandos Gradle:

```bash
./gradlew runClient    # Inicia o cliente
./gradlew runServer    # Inicia o servidor
```

**Nota:** Para debugar via terminal, você precisará usar a integração Gradle do seu editor ou configurar um debugger manualmente.

---

Para mais informações, consulte a [documentação oficial do Fabric](https://docs.fabricmc.net/1.21.1/develop/getting-started/launching-the-game).
