<?php

namespace Scheduler\Models;

class Project extends SemesterClass {

	public function getFormName(): string {
		return "projekt";
	}

	public function getStyle(): string {
		return "yellow";
	}

}
