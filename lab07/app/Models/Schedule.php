<?php

namespace Scheduler\Models;

use Scheduler\Interfaces\ItemClassInterface as ItemClass;

class Schedule {

	private $classes = [[]];

	private static $week_day_names = [
		"1" => "poniedziałek",
		"2" => "wtorek",
		"3" => "środa",
		"4" => "czwartek",
		"5" => "piątek",
	];

	private static $hour_slots = [
		"1" => "08:15-09:45",
		"2" => "10:00-11:30",
		"3" => "11:45-13:15",
		"4" => "13:30-15:00",
		"5" => "15:15-16:45",
		"6" => "17:00-18:30",
		"7" => "18:45-20:15",
	];

	public $typeList = ["wykład", "ćwiczenia", "wuef", "projekt", "laboratorium", "seminarium", "wydarzenia"];
	public $teacherList = ["Rewak", "Duda", "Fryźlewicz", "Nowak", "Klosow", "Selwat", "Kordecki", "Nadybski", "Twardoch", "Grzybowski"];
	public $roomList = ["A10", "A118", "A218", "C312", "C212", "C222", "D16", "C10", "C11"];

	public function getDays() {
		return self::$week_day_names;
	}

	public function getHours() {
		return self::$hour_slots;
	}

	public function hasClass(int $week_day, int $hour_slot): bool {
		return isset($this->classes[$week_day][$hour_slot]);
	}

	public function getClass(int $week_day, int $hour_slot): ItemClass {
		return $this->classes[$week_day][$hour_slot];
	}

	public function save(ItemClass $class, int $week_day, int $hour_slot) {
		$this->classes[$week_day][$hour_slot] = $class;
	}


}
