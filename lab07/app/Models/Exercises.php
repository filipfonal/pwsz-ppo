<?php

namespace Scheduler\Models;

class Exercises extends SemesterClass {

	public function getFormName(): string {
		return "ćwiczenia";
	}

  public function getStyle(): string {
		return "red";
	}

}
