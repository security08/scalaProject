package com.test.io

import java.io.PrintWriter
import java.io.File
import org.junit.Test

class WriteToFile {
  def writeToFile(fileName: String)(codeBlock: PrintWriter => Unit) = {
    val writer = new PrintWriter(new File(fileName))
    try { codeBlock(writer) } finally { writer.close() }
  }

  @Test
  def writeToFileTest {
    writeToFile("d:/test/output.txt") { writer =>
      writer write "hello from Scala"
    }
  }
}