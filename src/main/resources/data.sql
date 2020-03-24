
INSERT INTO coach (id, login)
VALUES (3, 'czacza');

INSERT INTO user (id, name, surname, password, login, coach_id)
VALUES (1, 'Arkadiusz' , 'Lizak' , 'password', 'arli', 3);
INSERT INTO user (id, name, surname, password, login)
VALUES (2, 'Czarek', 'Czarnecko', 'password', 'czacza');



INSERT INTO role (id, name )
VALUES(1, 'ROLE_USER');
INSERT INTO role (id, name )
VALUES(2, 'ROLE_COACH');

INSERT INTO user_role
VALUES (1, 1);
INSERT INTO user_role
VALUES (2, 1);
INSERT INTO user_role
VALUES (2, 2);

INSERT INTO exercise(id, name )
VALUES(1, 'przysiady');
INSERT INTO exercise(id, name )
VALUES(2, 'wyciskanie sztangi leżąc');
INSERT INTO exercise(id, name )
VALUES(3, 'podciąganie na drążku');
INSERT INTO exercise(id, name )
VALUES(4, 'OHP');
INSERT INTO exercise(id, name )
VALUES(5, 'uginanie ramion stojąc, naprzemian(biceps)');
INSERT INTO exercise(id, name )
VALUES(6, 'wyciskanie francuskie leżąc');
INSERT INTO exercise(id, name )
VALUES(7, 'rozciąganie');

INSERT INTO exercise(id, name )
VALUES(8, 'martwy ciąg');
INSERT INTO exercise(id, name )
VALUES(9, 'wiosłowanie sztangą');
INSERT INTO exercise(id, name )
VALUES(10, 'wyciskanie hantli pod skosem');
INSERT INTO exercise(id, name )
VALUES(11, 'unoszenie ramion bokiem');
INSERT INTO exercise(id, name )
VALUES(12, 'unszenia ramion do tyłu');
INSERT INTO exercise(id, name )
VALUES(13, 'modlitewnik');
INSERT INTO exercise(id, name )
VALUES(14, 'wyciskanie francuskie stojąc');

INSERT INTO workout (id, name, user_id)
VALUES (1, 'FBW-A', 1);
INSERT INTO workout (id, name, user_id)
VALUES (2, 'FBW-B', 1);

INSERT INTO workout_exercise (exercise_id, workout_id)
VALUES (1, 1);
INSERT INTO workout_exercise (exercise_id, workout_id)
VALUES (2, 1);
INSERT INTO workout_exercise (exercise_id, workout_id)
VALUES (3, 1);
INSERT INTO workout_exercise (exercise_id, workout_id)
VALUES (4, 1);
INSERT INTO workout_exercise (exercise_id, workout_id)
VALUES (5, 1);
INSERT INTO workout_exercise (exercise_id, workout_id)
VALUES (6, 1);
INSERT INTO workout_exercise (exercise_id, workout_id)
VALUES (7, 1);

INSERT INTO workout_exercise (exercise_id, workout_id)
VALUES (8, 2);
INSERT INTO workout_exercise (exercise_id, workout_id)
VALUES (9, 2);
INSERT INTO workout_exercise (exercise_id, workout_id)
VALUES (10, 2);
INSERT INTO workout_exercise (exercise_id, workout_id)
VALUES (11, 2);
INSERT INTO workout_exercise (exercise_id, workout_id)
VALUES (12, 2);
INSERT INTO workout_exercise (exercise_id, workout_id)
VALUES (13, 2);
INSERT INTO workout_exercise (exercise_id, workout_id)
VALUES (14, 2);
INSERT INTO workout_exercise (exercise_id, workout_id)
VALUES (7, 2);

INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 1, 70, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 1, 70, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 1, 70, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 1, 70, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 1, 70, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));


INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 2, 50, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 2, 50, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 2, 50, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 2, 50, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 2, 50, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));

INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 3, 0, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 3, 0, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 3, 0, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 3, 0, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 3, 0, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));

INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 4, 20, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 4, 20, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 4, 20, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 4, 20, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 4, 20, 5, null, TO_DATE('02/01/2020', 'dd/mm/yyyy'))
/////

INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 1, 75, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 1, 75, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 1, 75, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 1, 75, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 1, 75, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));


INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 2, 55, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 2, 55, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 2, 55, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 2, 55, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 2, 55, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));

INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 3, 0, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 3, 0, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 3, 0, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 3, 0, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 3, 0, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));

INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 4, 30, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 4, 30, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 4, 30, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 4, 30, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 4, 30, 5, null, TO_DATE('06/01/2020', 'dd/mm/yyyy'));

/////
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 1, 75, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 1, 75, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 1, 75, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 1, 75, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 1, 75, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));


INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 2, 55, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 2, 55, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 2, 55, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 2, 55, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 2, 55, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));

INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 3, 0, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 3, 0, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 3, 0, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 3, 0, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 3, 0, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));

INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 4, 30, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 4, 30, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 4, 30, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 4, 30, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));
INSERT INTO exercise_result (workout_id, exercise_id, load, reps, time_in_seconds, day_of_training)
VALUES ( 1, 4, 30, 5, null, TO_DATE('10/01/2020', 'dd/mm/yyyy'));