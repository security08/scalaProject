package com.test.traits

trait CreditCheck extends Check {
	override def check() : String = "Checked Credit..." + super.check
}