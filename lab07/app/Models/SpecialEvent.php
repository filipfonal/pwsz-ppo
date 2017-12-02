<?php

namespace Scheduler\Models;

use Scheduler\Interfaces\ItemClassInterface;

class SpecialEvent implements ItemClassInterface {

  private $name;

	public function __construct(string $name) {
		$this->name = $name;
	}

	public function getName(): string {
		return $this->name;
	}

  public function getFormName(): string {
		return "wydarzenia";
	}

  public function getStyle(): string {
		return "dark-red";
	}

}
