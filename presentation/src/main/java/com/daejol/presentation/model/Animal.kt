package com.daejol.presentation.model

sealed class Animal private constructor(
    val id: String,
    val name: String,
    val imageUrl: String,
    val weight: String,
    val temperament: List<String>,
    val origin: String,
    val description: String,
    val lifeSpan: String,
    val wikipediaUrl: String
) {
    class Cat(
        id: String = "",
        name: String = "Bengal",
        imageUrl: String = "https://cdn2.thecatapi.com/images/cqg.jpg",
        weight: String = "3 - 7",
        temperament: List<String> = listOf("Alert", "Agile", "Energetic", "Demanding", "Intelligent"),
        origin: String = "United States",
        description: String = "Bengals are a lot of fun to live with, but they're definitely not the cat for everyone, or for first-time cat owners. Extremely intelligent, curious and active, they demand a lot of interaction and woe betide the owner who doesn't provide it.",
        lifeSpan: String = "12 - 15",
        wikipediaUrl: String = "https://en.wikipedia.org/wiki/Bengal_(cat)"
    ) : Animal(id, name, imageUrl, weight, temperament, origin, description, lifeSpan, wikipediaUrl)

    class Dog(
        id: String = "",
        name: String = "Bengal",
        imageUrl: String = "https://cdn2.thecatapi.com/images/cqg.jpg",
        weight: String = "3 - 7",
        temperament: List<String> = listOf("Alert", "Agile", "Energetic", "Demanding", "Intelligent"),
        origin: String = "United States",
        description: String = "Bengals are a lot of fun to live with, but they're definitely not the cat for everyone, or for first-time cat owners. Extremely intelligent, curious and active, they demand a lot of interaction and woe betide the owner who doesn't provide it.",
        lifeSpan: String = "12 - 15",
        wikipediaUrl: String = "https://en.wikipedia.org/wiki/Bengal_(cat)"
    ) : Animal(id, name, imageUrl, weight, temperament, origin, description, lifeSpan, wikipediaUrl)
}
