# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and this project adheres to [NeoForged Semantic Versioning](https://docs.neoforged.net/docs/gettingstarted/versioning).

## [4.0.0](https://github.com/NovaMachina-Mods/NovaCore/compare/v3.0.0...v4.0.0) - 2025-01-15

### Added
- Registry key to `AbstractRegistry`
- `BlockBehavior.Properties` to block factory methods
- `Item.Properties` to item factory methods
- `BucketItem` factory method
- Add tag to tag

### Changed
- Update to 1.21.4
- Require NeoForge 21.4.20-beta
-
### Removed
- Burnable block factory method
- Conditions from `LootModifierDefinition`
- `IngredientUtils` is essentially defunct
- Fluid textures are no longer set in `FluidDefinition`

## [3.0.0](https://github.com/NovaMachina-Mods/NovaCore/compare/v2.0.0...v3.0.0) - 2024-07-09

### Added
- `IServiceProvider` to work more towards framework-agnostic library
- Factory classes for `BlockItem`, `Block`, and `BlockEntityType`
- Orrientable blockstate functions for data generation
- Menu and Screen support
- Unit testing for some registries

### Changed
- Updated to NeoForge 21.0.40-beta
- Move NeoForge Registry classes to `novamachina.novacore.bootstrap.core.registries`

### Removed
- `BlockBuilder` class. Use `BlockBehavior.Properties` and factory functions from `BlockRegistry`

## [2.0.0](https://github.com/NovaMachina-Mods/NovaCore/compare/v1.0.0...v2.0.0) - 2024-03-14

### Added
- BlockState provider
- Recipe provider
- Abstract Recipe and RecipeSerializer

### Changed
- Updated to NeoForge 20.4.167
- Replace all references of Forge with NeoForge
- Replace `ForgeRegistries` with either `BuiltInRegistries` or `NeoForgeRegistries`
- Use Codec encoding for `FluidStack` and `ItemStack`
- Replace `ExistingFileHelper` in `RecipeProvider` with `CompletableFuture<HolderLookup.Provider>`
- `Consumer<FinishedRecipe>` -> `RecipeOutput`
- `AbstractLangGenerator` is now abstract
- Falling block factory method returns ColoredFallingBlock with color value -8356741

### Removed
- Lombok
- Some `@NonNull` and `@Nullable`
- Removed `id` parameter from `Recipe` as it is no longer used by Vanilla

## [1.0.0](https://github.com/NovaMachina-Mods/NovaCore/compare/v1.0.0) - 2023-10-24

### Added

- Requires NeoForge 47.1.55+
- Bootstrap classes for Neoforge registries
- Common Block Entity renderer
- Mostly framework agnostic definitions for:
  - Blocks
  - Block Entities
  - Creative Tabs
  - Fluids
  - Items
  - Loot Modifiers
  - Recipe Serializers
- Framework agnostic registries for:
  - Block Entity Types
  - Block
  - Creative Tabs
  - Fluids
  - Items
  - Loot Modifiers
  - Recipe Serializers
  - Recipe Types
  - Sound Events
- Data Generation classes for:
  - Recipes
  - Loot Tables
  - Tags
  - Language
- Common utility classes
