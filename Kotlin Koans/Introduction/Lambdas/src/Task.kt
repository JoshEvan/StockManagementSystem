fun containsEven(collection: Collection<Int>): Boolean =
        collection.any { a -> a%2 == 0 }
