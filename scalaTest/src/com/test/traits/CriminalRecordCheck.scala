package com.test.traits

trait CriminalRecordCheck extends Check {
	override def check() : String = "Check Criminal Records..." + super.check()
}