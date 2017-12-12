<?php

namespace Scheduler\Models;

use Scheduler\Interfaces\ItemClassInterface;

abstract
class SemesterClass implements ItemClassInterface {

	private $name;
	private $teacher;
	private $room;

	public function __construct(string $name, string $teacher, string $room) {
		$this->name    = $name;
		$this->teacher = $teacher;
		$this->room    = $room;
	}

	public function getName():string {
		return $this->name;
	}

	public function getTeacher():string {
		return $this->teacher;
	}

	public function getRoom():string {
		return $this->room;
	}

}
