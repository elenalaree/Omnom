import java.util.Scanner

fun main() {
//    uses the java scanner
    val scanner = Scanner(System.`in`)

//    calls in the data class for the Recipe Manager
    val recipeManager = RecipeManager()

//  While true, keep showing the list.
    while (true) {
        println("\nRecipe Manager")
        println("1. Add Recipe")
        println("2. Find Recipe")
        println("3. Remove Recipe")
        println("4. Exit")
        print("Enter your choice: ")

//        When there is a choice made, follow path.
        when (scanner.nextInt()) {

//            Path 1, enter recipe.
            1 -> {
                println("\nEnter Recipe Details:")
                println("Name: ")
                val name = readln()
                println("Ingredients (comma-separated): ")
                val ingredientList: String = readln()
                println("Instructions: ")
                val instructions = readln()
                val ingredients = ingredientList.split(",").map {it.trim() }
                val recipe = Recipe(name, ingredients, instructions)
                recipeManager.addRecipe(recipe)
                println("Recipe added successfully!")
            }

//            Path 2, find recipe
            2 -> {
                print("\nEnter Recipe Name to Find: ")
                val nameToFind = scanner.next()
                val foundRecipe = recipeManager.findRecipeByName(nameToFind)
                if (foundRecipe != null) {
                    println("\nFound Recipe:")
                    println("Name: ${foundRecipe.name}")
                    println("Ingredients: ${foundRecipe.ingredients.joinToString(", ")}")
                    println("Instructions: ${foundRecipe.instructions}")
                } else {
                    println("Recipe not found.")
                }
            }

//            Path 3, remove recipe
            3 -> { print("\nEnter Recipe Name to Remove: ")
                    val nameToRemove = scanner.next()
                    val removed = recipeManager.removeRecipeByName(nameToRemove)
                    if (removed) {
                        println("Recipe removed successfully!")
                    } else {
                        println("Recipe not found.")
                    }

            }
//            Path 4, exit program.
            4 -> {
                println("Exiting the Recipe Manager. Goodbye!")
                return
            }
//            If not any of the 4, re-loop
            else -> {
                println("Invalid choice. Please select a valid option.")
            }
        }
    }
}

//Create the data class for recipe
data class Recipe(val name: String, val ingredients: List<String>, val instructions: String)

//Make a class to hold recipe information.
class RecipeManager {
    private val recipes = mutableListOf<Recipe>()

//    Add recipe function
    fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
    }

//    Find recipe function
    fun findRecipeByName(name: String): Recipe? {
        return recipes.find { it.name.equals(name, ignoreCase = true) }
    }

//    remove recipe function.
    fun removeRecipeByName(name: String): Boolean {
        val recipeToRemove = recipes.find { it.name.equals(name, ignoreCase = true) }
        return if (recipeToRemove != null) {
            recipes.remove(recipeToRemove)
            true
        } else {
            false
        }
    }
}