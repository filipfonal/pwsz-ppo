<?php

require "./vendor/autoload.php";

use Scheduler\Models\Lecture;
use Scheduler\Models\PE;
use Scheduler\Models\Lab;
use Scheduler\Models\Lectureship;
use Scheduler\Models\Project;
use Scheduler\Models\Seminar;
use Scheduler\Models\Exercises;
use Scheduler\Models\SpecialEvent;
use Scheduler\Models\Schedule;

$twig = new Twig_Environment(new Twig_Loader_Filesystem("/"), []);

$schedule = new Schedule();

$schedule->save(new Lecture("Bazy danych", "Klosow", "C212"), 1, 1);
$schedule->save(new Lecture("Programowanie i projektowanie obiektowe", "Klosow", "C212"), 1, 2);
$schedule->save(new Exercises("Język Angielski", "Twardoch", "C312"), 1, 3);
$schedule->save(new Lecture("Sieci kompuetowe", "Nadybski", "C212"), 1, 4);
$schedule->save(new Lab("Sieci kompuetowe", "Nadybski", "C11"), 1, 5);
$schedule->save(new Lecture("Podstawy teorii informacji", "Grzybowski", "A118"), 2, 3);
$schedule->save(new Lecture("Podstawy teorii informacji", "Grzybowski", "A118"), 2, 4);
$schedule->save(new Lecture("Podstawy metod probabilistycznych i statystyki", "Selwat", "C212"), 3, 1);
$schedule->save(new Exercises("Podstawy metod probabilistycznych i statystyki", "Selwat", "C222"), 3, 2);
$schedule->save(new Lab("Architektura komputerów", "Duda", "D16"), 3, 3);
$schedule->save(new Lab("Projektowanie i programowanie obiektowe", "Rewak", "C10"), 3, 5);
$schedule->save(new PE("Siłownia", "Nowak", "A10"), 4, 1);
$schedule->save(new Seminar("Programowanie mobilne", "Fryźlewicz", "C10"), 4, 2);
$schedule->save(new Project("Aplikacja internetowa", "Kordecki", "C212"), 4, 5);
$schedule->save(new SpecialEvent("Konferencja dla młodych programistów"), 5, 2);

echo $twig->render("index.twig", [
    "schedule" => $schedule,
]);
