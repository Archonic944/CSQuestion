# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and Run Commands
- Compile a file: `javac -d out/production/CSQuestion/ src/FileName.java`
- Run a specific program: `java -cp out/production/CSQuestion/ FileName`
- Run all programs: Use IntelliJ IDEA's run configurations

## Code Style Guidelines
- **Indentation**: 4 spaces (no tabs)
- **Braces**: Same line for opening braces
- **Imports**: Specific imports only (no wildcards), organized by standard Java imports first
- **Naming**: 
  - Classes: PascalCase (e.g., `SudokuSolver`)
  - Methods/variables: camelCase (e.g., `findSolution`)
  - Constants: UPPER_SNAKE_CASE (e.g., `MAX_SIZE`)
- **Types**: Use generics appropriately with single-letter type parameters (K, V, T)
- **Error Handling**: Catch exceptions and use `e.printStackTrace()` or return null for errors
- **Documentation**: Use JavaDoc style comments for public methods
- **File Structure**: Each file typically represents one algorithm challenge solution
- **Utils**: Place reusable code in the utils package

## Project Organization
- Problem solutions in root of src directory
- Utility classes in src/utils/
- Problem descriptions in my_problems/