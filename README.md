## CircleCI badge
[![CircleCI](https://dl.circleci.com/status-badge/img/gh/Ibrahim-Jallouli/ceri-m1-techniques-de-test/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/Ibrahim-Jallouli/ceri-m1-techniques-de-test/tree/master)

## Codecov badge
[![codecov](https://codecov.io/gh/Ibrahim-Jallouli/ceri-m1-techniques-de-test/graph/badge.svg?token=99T2G064EK)](https://codecov.io/gh/Ibrahim-Jallouli/ceri-m1-techniques-de-test)



notes: 
- j'ai utilisé junit5 pour les tests car il est plus récent et plus performant que junit4 (juint4 > 15 ans)
- Pour l'intégration avec Codecov : lors de la configuration de Codecov avec GitHub et l'ajout du workflow et de main.yml, ainsi que la configuration des différents outils pour générer les rapports de couverture, j'ai rencontré un problème avec GitHub Actions. Lors de l'exécution du workflow, une erreur s'affiche (dans la partie des tests : java.lang.NullPointerException), alors que tout fonctionne correctement en local 🤔. Après avoir effectué 40 commits 🥲 pour résoudre ce problème, j'ai constaté que le problème est lié à l'annotation @ExtendWith(MockitoExtension.class) (JUnit 5), qui est utilisée pour initialiser les mocks.

  J'ai également remarqué que @BeforeEach est utilisé pour initialiser les mocks. Cependant, remplacer @ExtendWith(MockitoExtension.class) par @BeforeEach n'a pas résolu le problème et n'a pas initialisé les mocks. C'est pourquoi il y a une duplication de code dans chaque méthode de test.


- finalement j'ai utilisé CircleCI pour la CI/CD et Codecov pour la couverture de test. j'ai ajouté les testes pour les getters pour avoir une couverture de 100%.
