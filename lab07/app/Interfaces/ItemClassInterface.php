<?php

namespace Scheduler\Interfaces;

interface ItemClassInterface {

	public function getName(): string;
	public function getFormName(): string;
	public function getStyle(): string;

}
