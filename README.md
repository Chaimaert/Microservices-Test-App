# AI Test Automation Platform

Bienvenue dans le projet AI Test Automation Platform, une plateforme conçue pour automatiser les tests logiciels à l'aide de microservices et d'une interface intuitive. Ce projet utilise des technologies modernes comme React pour le frontend et Spring Boot pour le backend. Voici une documentation détaillée pour comprendre et démarrer avec le projet.

## Table of Contents
- [Problématique](#problématique)
- [Solution proposée ](#solution-proposée )
- [Impact](#impact)
- [Architecture](#architecture)
- [Technologies utilisées](#technologies-utilisées)
- [Prérequis](#prérequis)
- [Démonstration](#démonstration)
  
## Problématique

Les équipes de développement consacrent beaucoup de temps à la création et à l'exécution manuelle des tests unitaires et fonctionnels. Cela peut entraîner :

- Des oublis ou des erreurs humaines,
- Une couverture de test insuffisante,
- Une perte de productivité.

## Solution proposée 

Ce projet propose un logiciel basé sur l'IA, capable de :

- Générer automatiquement des scénarios de tests unitaires et fonctionnels,
- Utiliser des algorithmes d'apprentissage automatique pour analyser le code source, optimiser la couverture de test, et identifier les cas d'erreurs potentielles,
- Proposer des correctifs ou ajustements en cas de test échoué afin d'améliorer la conformité avec les exigences du projet.

## Impact 

- Réduction des erreurs humaines dans les tests,
- Augmentation de la couverture et de la qualité des tests,
- Amélioration de la productivité des équipes de développement,
- Réduction des coûts liés à la maintenance du code.

## Architecture

Le projet est basé sur une architecture microservices. Chaque microservice gère une fonctionnalité spécifique, ce qui permet une flexibilité et une scalabilité accrue. Voici les principaux composants :

![Architecture_Projet](https://github.com/user-attachments/assets/77848378-f5fd-4f14-85ac-5bec85e6d190)

 1. Microservices :
    - Eureka Server : Serveur de registre pour la découverte des services.
    - Gateway : Point d'entrée unique pour le routage des requêtes vers les microservices.
    - Microservice d'Authentification : Ce microservice est responsable de la gestion de l'authentification et de l'autorisation des utilisateurs.
    - GenerateTest Microservice : Génération de cas de test basés sur le code utilisateur.
    - AnalyzeCode Microservice : Analyse des tests générés pour évaluer leur qualité.
    - ReportGenerator Microservice : Génération de rapports incluant le code soumis, les tests générés, et les analyses. 

 2. Frontend :
    - ReactJs : Interface utilisateur permettant de soumettre du code, afficher les résultats et consulter les rapports.
    - React Router (Gestion de la navigation)
    - Axios (Appels API HTTP)

 3. Base de Données :
    - MySQL & PostGress : Stockage des métadonnées des rapports et autres informations pertinentes.

 4. Autres outils :
    - Postman : Test des API.
    - IntelliJ IDEA : IDE pour le développement backend.
    - Visual Studio Code : IDE pour le développement frontend. 

## Technologies utilisées

- Spring Boot : Pour le développement des microservices.
- React : Pour le frontend.
- Docker & Docker Compose : Pour la conteneurisation et l'orchestration.
- Eureka : Pour la découverte des services.
- MySQL, PostGress : Pour le stockage des données.

## Prérequis



## Démonstration


