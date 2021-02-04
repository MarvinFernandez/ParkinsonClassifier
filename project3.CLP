(deftemplate Patient (slot name)
					(slot surname)
                     (slot age)
                     (slot sex)
                     (slot both_sides)
                     (slot tremor)
                     (slot posture)
                     (slot daily_activities)
                     (slot gait)
                     (slot voice)
                     (slot final_score)
                     (slot stage)
                     (slot limp  )
                     (slot slow_walking )
                     (slot falling_freq )
                     (slot eat )
                     (slot dress )
                     (slot dependency )
                     (slot retropulsion )
                     (slot pulltest )
                     (slot stoopedover )
                     (slot softvoice )
                     (slot stutter )
                     (slot fasttalk )
                     (slot diagnosis (default no))
		     		(slot questions (default no))
                     (slot controlsides (default no))
                     (slot treatment)
                     )

   

(defrule Sides1
   (declare (salience 97))
   ?p <- (Patient (both_sides no) (diagnosis no) (questions no))
   =>
   (modify ?p (final_score -1)(stage 1)(questions yes)(controlsides yes))
   )

(defrule Sides2
   (declare (salience 96))
   ?p <- (Patient (both_sides yes) (diagnosis no) (controlsides no))
   =>
   (modify ?p (controlsides yes))
   )
 
(defrule GetGait
   (declare (salience 89))
   ?p <- (Patient (stage nil) (limp ?l) (slow_walking ?s) (falling_freq ?f) (diagnosis no))
   (test (numberp ?l))
   (test (numberp ?s))
   (test (numberp ?f))
   =>
   (modify ?p (gait (+ ?l ?s ?f))))
   

(defrule GetDaily_Activities
   (declare (salience 82))
   ?p <- (Patient (stage nil) (eat ?e) (dress ?d) (dependency ?de) (diagnosis no))
   (test (numberp ?e))
   (test (numberp ?d))
   (test (numberp ?de))
   =>
   (modify ?p (daily_activities (+ ?e ?d (* ?de 2)))))
   
(defrule GetPosture
   (declare (salience 75))
   ?p <- (Patient (stage nil) (retropulsion ?r) (pulltest ?pu) (stoopedover ?s) (diagnosis no))
   (test (numberp ?r))
   (test (numberp ?pu))
   (test (numberp ?s))
   =>
   (modify ?p (posture (+ ?r (* ?pu 2) ?s))))

(defrule Getvoice
   (declare (salience 70))
   ?p <- (Patient (softvoice ?s) (stutter ?st) (fasttalk ?f))
   (test (numberp ?s))
   (test (numberp ?st))
   (test (numberp ?f))
   =>
   (modify ?p (voice (+ ?s ?st ?f))))
   

(defrule GetFinalScore
   (declare (salience 66))
   ?p <- (Patient (tremor ?t) (posture ?po) (daily_activities ?d) (gait ?g) (voice ?v) (diagnosis no) (questions no))
   (test (numberp ?t))
   (test (numberp ?po))
   (test (numberp ?d))
   (test (numberp ?g))
   (test (numberp ?v))
   =>
   (modify ?p (final_score (+ ?t ?po ?d ?g ?v)) (questions yes))
   )

(defrule GetStage2
   (declare (salience 65))
   ?p <- (Patient (final_score ?f) (diagnosis no) (questions yes))
   (test (numberp ?f))
   (test (> ?f 0))
   (test (< ?f 5))
   =>
   (modify ?p (stage 2)))

(defrule GetStage3
   (declare (salience 64))
   ?p <- (Patient (final_score ?f) (diagnosis no) (questions yes))
   (test (numberp ?f))
   (test (> ?f 4))
   (test (< ?f 9))
   =>
   (modify ?p (stage 3)))

(defrule GetStage4
   (declare (salience 63))
   ?p <- (Patient (final_score ?f) (diagnosis no) (questions yes))
   (test (numberp ?f))
   (test (> ?f 8))
   (test (< ?f 13))
   =>
   (modify ?p (stage 4)))

(defrule GetStage5
   (declare (salience 62))
   ?p <- (Patient (final_score ?f) (diagnosis no) (questions yes))
   (test (numberp ?f))
   (test (> ?f 12))
   (test (< ?f 18))
   =>
   (modify ?p (stage 5)))
   
   
(defrule AddNewTreatment_Stage1
   (declare (salience 59))
   ?pa <- (Patient (stage 1) (diagnosis no) (treatment nil))
   =>
   (modify ?pa (diagnosis yes) (treatment Amatadine))
   )
   
   
(defrule AddNewTreatment_Stage2
   (declare (salience 59))
   ?pa <- (Patient (stage 2) (diagnosis no) (treatment nil))
   =>
   (modify ?pa (diagnosis yes) (treatment Anticholinergics))
   )
 
   
(defrule AddNewTreatment_Stage3
   (declare (salience 59))
   ?pa <- (Patient (stage 3) (diagnosis no) (treatment nil))
   =>
   (modify ?pa (diagnosis yes) (treatment Levodopa))
   )
   
   
(defrule AddNewTreatment_Stage4
   (declare (salience 59))
   ?pa <- (Patient (stage 4) (diagnosis no) (treatment nil))
   =>
   (modify ?pa (diagnosis yes) (treatment DBS))
   ) 
   

(defrule AddNewTreatment_Stage5
   (declare (salience 59))
   ?pa <- (Patient (stage 5) (diagnosis no) (treatment nil))
   =>
   (modify ?pa (diagnosis yes) (treatment Brain_surgery))
   ) 



   
   


