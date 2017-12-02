<?php

namespace Scheduler\Models;

class Seminar extends SemesterClass {

	public function getFormName(): string {
		return "seminarium";
	}

	public function getStyle(): string {
		return "purple";
	}

}
