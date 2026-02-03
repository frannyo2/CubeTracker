# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

CubeTracker is a Java CLI application that retrieves and displays speedcubing data from the World Cube Association (WCA) REST API. It fetches competitor profiles, world rankings, and competition data.

## Build Commands

```bash
./gradlew build    # Build with tests
./gradlew run      # Run the CLI application (stdin enabled)
./gradlew test     # Run JUnit 5 tests
./gradlew clean    # Clean build artifacts
```

## Architecture

Three-layer architecture:

1. **Presentation** (`App.java`) - CLI menu using Scanner for user input
2. **API Client** (`api/WcaClient.java`) - HTTP requests via OkHttp3, JSON parsing with Gson
3. **Models** (`models/*.java`) - DTOs matching WCA API response structures

Data flows: User input → App → WcaClient → WCA REST API → Models → Console output

## External API

Base URL: `https://raw.githubusercontent.com/robiningelbrecht/wca-rest-api/master/api`

Key endpoints used:
- `/persons/{wcaID}.json` - Individual cuber data
- `/rank/world/single/333.json` - 3x3 single world rankings
- `/rank/world/average/333.json` - 3x3 average world rankings
- `/continents.json` - Continent listing

## Tech Stack

- Java 21
- Gradle 9.0.0 (Kotlin DSL)
- OkHttp3 5.3.2
- Gson 2.13.2
- JUnit Jupiter 5.12.1
