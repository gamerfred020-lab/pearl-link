# Pearl Link (Minecraft 26.2)

Client-side Fabric mod that lets two players link together.

## Commands
- `/pearl link <player>` – link to another player
- `/pearl unlink` – remove the link
- `/pearl status` – show who you are linked to

## Keybind
Default: **G** (changeable in Options → Controls → Key Binds → Pearl Link)

When either linked player presses the keybind, the **other** player will automatically right-click **only if** they are currently looking at a trapdoor.

## How it works
Uses private messages (`/msg`) under the hood. The trigger messages are hidden from chat.

Both players need the mod installed. Works on any server that allows private messages.

## Building
Requires **Java 25**.

```bash
./gradlew build
```

The jar will be in `build/libs/pearllink-1.0.0.jar`.

Or just download the artifact from the Actions tab after a successful build.

## Installation
1. Install Fabric Loader for Minecraft 26.2
2. Install Fabric API for 26.2
3. Put `pearllink-1.0.0.jar` in your mods folder
