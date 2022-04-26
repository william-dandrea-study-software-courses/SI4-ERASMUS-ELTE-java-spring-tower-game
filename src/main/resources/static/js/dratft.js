const val = {
    "settings": {
        "generalSettings": {
            "widthBoard": 10,
                "lengthBoard": 10,
                "radiusToPlaceBuilding": 3,
                "ennemyForbiddenRadiusForBuilding": 2
        },
        "castelSettings": {
            "initialHealthPoints": 200,
                "healthPointsRemovedWhenSoldierReachCastle": 10
        },
        "goldSettings": {
            "initialAmountOfGold": 100,
                "addedGoldAtEachRound": 30,
                "priceOfGoldMine": 200,
                "addedGoldAtEachRoundWithGoldMine": 30
        },
        "monsterSettings": {
            "poppingMonsterAtEachNRounds": 10,
                "roundsFrequencyOfPopping": 3
        },
        "obstacleSettings": {
            "numberOfObstacles": 2,
                "radiusOfObstacles": 3
        },
        "soldierMainSettings": {
            "anything": 0
        },
        "fastSoldierSettings": {
            "price": 50,
                "initialHealthPoints": 30,
                "numberOfMovesAtEachRound": 5,
                "numberOfTileHeCanJump": 2
        },
        "flightSoldierSettings": {
            "price": 30,
                "initialHealthPoints": 20,
                "numberOfMovesAtEachRound": 5
        },
        "killerSoldierSettings": {
            "price": 50,
                "initialHealthPoints": 40,
                "numberOfMovesAtEachRound": 5,
                "damagesInflictedToOtherSoldiers": 5
        },
        "towerMainSettings": {
            "anything": 0
        },
        "freezeTowerSettings": {
            "price": 30,
                "shootingRange": 3,
                "numberOfRoundsWhereTheSoldierInTheAreaAreFreeze": 1
        },
        "normalTowerSettings": {
            "price": 25,
                "shootingRange": 3,
                "numberOfSimultaneousStrikes": 3
        },
        "sniperTowerSettings": {
            "price": 50,
                "shootingRange": 5,
                "numberOfSimultaneousStrikes": 2
        }
    },
    "board": {
        "dimension": {
            "length": 10,
                "width": 10
        },
        "tiles": [

            {
                "position": {
                    "x": 9,
                    "y": 9
                }
            }
        ],
            "obstacles": [
            {
                "position": {
                    "x": 1,
                    "y": 3
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 0,
                    "y": 4
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 1,
                    "y": 4
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 2,
                    "y": 4
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": -1,
                    "y": 5
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 0,
                    "y": 5
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 1,
                    "y": 5
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 2,
                    "y": 5
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 3,
                    "y": 5
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 0,
                    "y": 6
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 1,
                    "y": 6
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 2,
                    "y": 6
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 1,
                    "y": 7
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 7,
                    "y": 3
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 6,
                    "y": 4
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 7,
                    "y": 4
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 8,
                    "y": 4
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 5,
                    "y": 5
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 6,
                    "y": 5
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 7,
                    "y": 5
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 8,
                    "y": 5
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 9,
                    "y": 5
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 6,
                    "y": 6
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 7,
                    "y": 6
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 8,
                    "y": 6
                },
                "name": "obstacle_entity"
            },
            {
                "position": {
                    "x": 7,
                    "y": 7
                },
                "name": "obstacle_entity"
            }
        ]
    },
    "player1": {
        "id": 1,
        "currentGold": 100,
        "castle": {
            "position": {
                "x": 7,
                "y": 0
            },
            "name": "castle_entity",
            "healthPoint": 200
        },
        "entities": []
    },
    "player2": {
        "id": 1,
        "currentGold": 100,
        "castle": {
            "position": {
                "x": 5,
                "y": 9
            },
            "name": "castle_entity",
            "healthPoint": 200
        },
        "entities": []
    }
}
