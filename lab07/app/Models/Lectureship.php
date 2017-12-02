<?php

namespace Scheduler\Models;

class Lectureship extends SemesterClass {

	public function getFormName(): string {
		return "lektorat";
	}

	public function getStyle(): string {
		return "gray";
	}

}
