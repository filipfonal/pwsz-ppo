<?php

namespace Scheduler\Models;

class Lab extends SemesterClass {

	public function getFormName(): string {
		return "laboratorium";
	}

	public function getStyle(): string {
		return "orange";
	}

}
