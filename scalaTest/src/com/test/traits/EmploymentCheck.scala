package com.test.traits

trait EmploymentCheck extends Check {
	override def check() : String = "Checked Employment..." + super.check()
}