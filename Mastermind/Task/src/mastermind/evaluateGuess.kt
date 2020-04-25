package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    val secretCol = secret.asIterable().asSequence().toList()
    val guessCol = guess.asIterable().asSequence().toList()
    var rightPos = 0
    var wrongPos = 0
    val secretRem =  secretCol.toMutableList();
    val secretMap = mutableMapOf<Int, Char>();
    secretCol.forEachIndexed{ i,e ->
        secretMap.put(i, e);
    }
    var foundIndexMap = mutableMapOf<Int, Char>()
    guessCol.forEachIndexed{ idx, e ->
        if (secretMap.get(idx) == e) {
            rightPos++;
            foundIndexMap.put(idx, e)
            secretRem.remove(e);
        }
    }
    guessCol.forEachIndexed{ idx, e ->
        if (!foundIndexMap.containsKey(idx)
                && !secretRem.contains(e)) {
            secretRem.remove(e)
            wrongPos++;
        }
    }

    return Evaluation(rightPos, wrongPos)
}
