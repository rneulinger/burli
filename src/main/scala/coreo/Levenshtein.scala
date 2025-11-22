package coreo

object Levenshtein {
  def distance(s1: String, s2: String): Int = {
    val len1 = s1.length
    val len2 = s2.length
    val dim = len1.max(len2) + 1
    val dp: Array[Array[Int]] = Array.ofDim(dim, dim)

    for (i <- 0 to len1) dp(i)(0) = i
    for (j <- 0 to len2) dp(0)(j) = j

    for (i <- 1 to len1) {
      for (j <- 1 to len2) {
        val cost = if (s1(i - 1) == s2(j - 1)) 0 else 1
        dp(i)(j) = List(
          dp(i - 1)(j) + 1, // deletion
          dp(i)(j - 1) + 1, // insertion
          dp(i - 1)(j - 1) + cost // substitution
        ).min
      }
    }
    dp(len1)(len2)
  }

  @main def run(): Unit = {
    println(distance("kitten", "sitting")) // Output: 3
    println(distance("scala", "scala")) // Output: 0
    println(distance("flaw", "lawn")) // Output: 2
  }
}