fun joinOptions(options: Collection<String>) =
        options.joinToString(
                separator =", ",
                prefix = "[",
                postfix = "]",
        )
