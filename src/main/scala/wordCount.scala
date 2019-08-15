import org.apache.spark.{SparkConf, SparkContext}

object wordCount {
  def main(args: Array[String]): Unit = {
    val wordFile = "file:///data/word.txt"
    val conf = new SparkConf()
      .setMaster("spark://192.168.1.128:7070").
      setAppName("wordcount");
    val sc = new SparkContext(conf)
    val input = sc.textFile(wordFile, 2).cache()
    val lines = input.flatMap(line => line.split(" "))
    val count = lines.map(word => (word, 1)).reduceByKey { case (x, y) => x + y }
    val output = count.saveAsTextFile("/home/root/wordcout")
  }
}