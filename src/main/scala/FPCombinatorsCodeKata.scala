


  object FPCombinatorsCodeKata extends App {
      " SCALA FP COMBINATORS CODE KATA - start with expression 'ma flatMap f' and keep refactoring it by  "
      " applying each of the following rewrite rules in turn, until you get back to 'ma flatMap f'        "
 '①'; " flatmap can be defined in terms of map and flatten ......................................" ;'①'
 '②'; " map can be defined in terms of flatMap and pure ........................................." ;'②'
 '③'; " flatten can be defined in terms of flatMap and identity ................................." ;'③'
 '④'; " chained flatMaps are equivalent to nested flatMaps (flatMap associativity law) .........." ;'④'
 '⑤'; " Kleisli composition can be defined in terms of flatMap (apply this the other way around) " ;'⑤'
 '⑥'; " the identity function can be defined in terms of flatten and pure ......................." ;'⑥'
 '⑦'; " pure followed by flatten cancel each other out .........................................." ;'⑦'
 '⑧'; " pure is the identity function for Kleisli composition, so (f >=> pure) is the same as f.." ;'⑧'
                              ///////////////////////////                                               ///////////////////////////////////////////////////
                              // REFACTORED EXPRESSION //                                               // EQUIVALENCE APPLIED TO REWRITE RHS EXPRESSION //
                              ///////////////////////////                                               ///////////////////////////////////////////////////
 '①'; assert((ma flatMap f) == (ma map f flatten)                                        ) ;'①'; assert((ma flatMap f) == (ma map f flatten) )
 '②'; assert((ma flatMap f) == (ma flatMap (pure compose f) flatten)                     ) ;'②'; assert((ma map f) == (ma flatMap (pure compose f)))
 '③'; assert((ma flatMap f) == (ma flatMap (pure compose f) flatMap identity)            ) ;'③'; assert((mma flatten) == (mma flatMap identity) )
 '④'; assert((ma flatMap f) == (ma flatMap (a => (pure compose f)(a) flatMap identity))  ) ;'④'; assert(((ma flatMap f) flatMap g) == (ma flatMap (a => f(a) flatMap g)))
 '⑤'; assert((ma flatMap f) == (ma flatMap ((pure compose f) >=> identity))              ) ;'⑤'; assert((f >=> g)(n) == ((a:Int) => f(a) flatMap g)(n))
 '⑥'; assert((ma flatMap f) == (ma flatMap ((pure compose f) >=> (flatten compose pure)))) ;'⑥'; assert(identity(ma) == (flatten compose pure[List[Int]])(ma) )
 '⑦'; assert((ma flatMap f) == (ma flatMap (f >=> pure))                                 ) ;'⑦'; // pure followed by flatten cancel each other out
 '⑧'; assert((ma flatMap f) == (ma flatMap f)                                            ) ;'⑧'; assert(((f >=> pure)(n)) == f(n))
 `🏆`; assert((ma flatMap f) == List(1,1,1,2,2,2,3,3,3)                                   ) ;`🏆`; // back to the original expression: well done!

    lazy val    n : Int = 3
    lazy val   ma : List[Int] = List(1, 2, 3)
    lazy val  mma : List[List[Int]] = List(List(1, 2), List(3, 4))
    lazy val    f : (Int) => List[Int] = x => List(x, x, x)
    lazy val    g : (Int) => List[Int] = x => List(x, x, x)
    def    pure[A]: A => List[A] = List(_)
    def flatten[A]: List[List[A]] => List[A] = _ flatten
    implicit class ListFunctionOps[A,B](f:A=>List[B] ) {
      def >=>[C](g: B => List[C]): A => List[C] =
        a => f(a).foldRight(List[C]())((b, cs) => g(b) ++ cs)
    }
    val `🏆` = "well done!";
  }

