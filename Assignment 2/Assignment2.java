public class Assignment2 {

	/*-----------------------
	 *| Part A - tasks 1-11 |
	 * ----------------------*/
	
	// task 1
	public static boolean isSquareMatrix(boolean[][] matrix) {
		boolean isSquare = true;
		int number_of_rows = matrix.length;
		// to check if n = 0
		if(matrix.length == 0) {
			isSquare = false;
		}
		// to check if every row length is n
		for (int i=0 ; i < matrix.length & isSquare ; i=i+1) {
			int number_of_columns = matrix[i].length;
			if(number_of_rows != number_of_columns) {
				isSquare = false;
			}
		}
		return isSquare;
	}
	
	// task 2
	public static boolean isSymmetricMatrix(boolean[][] matrix) {
		boolean isSymmetric = true;
		
		// to check only the the entries above the diagonal because its square.
		for(int i = 0 ; i < matrix.length - 1 & isSymmetric ; i = i + 1) {
			for(int j = i + 1 ; j < matrix.length & isSymmetric ; j = j + 1) {
				if(matrix[i][j] != matrix[j][i]) {
					isSymmetric = false;
				}
			}
		}
		return isSymmetric;
	}

	// task 3
	public static boolean isAntiReflexiveMatrix(boolean[][] matrix) {
		boolean isAntiReflexive = true;
		
		// to check if all the entries on diagonal are false.
		for(int i=0 ; i < matrix.length & isAntiReflexive ; i=i+1) {
			if(matrix[i][i] != false) {
				isAntiReflexive = false;
			}
		}
		return isAntiReflexive;
	}
	
	// task 4
	public static boolean isLegalInstance(boolean[][] matrix) {
		boolean isLegal = true;
		
		// I used || to check first if the Matrix is square, because the other functions supposed that the Matrix is square.
		if(!isSquareMatrix(matrix) || !isSymmetricMatrix(matrix) || !isAntiReflexiveMatrix(matrix)) {
			isLegal = false;
		}
		
		return isLegal;
	}
	
	// task 5
	public static boolean isPermutation(int[] array) {	
		boolean isPermutation = true;
		/*
		if(array.length == 0) {
			isPermutation = false;
		}
		*/
		
		boolean[] helper_array = new boolean[array.length];
		for(int i=0 ; i < array.length ; i=i+1) {
			helper_array[i] = false;
		}
		
		for(int i=0 ; i < array.length & isPermutation ; i=i+1) {
			// I used || to ensure first if i is in array range then to use it as an index in array.
			if(array[i] >= array.length || array[i] < 0 || helper_array[array[i]] == true) {
				isPermutation = false;
			}else {
				helper_array[array[i]] = true;
			}
		} 
		
		for(int i=0 ; i < array.length & isPermutation ; i=i+1) {
			if(helper_array[i] == false) {
				isPermutation = false;
			}
		}
		return isPermutation;
	}
	
	// task 6
	public static boolean hasLegalSteps(boolean[][] flights, int[] tour) {
		boolean isLegal = true;
		//isLegal = isPermutation(tour);
		
		for(int i=0 ; i < tour.length-1 & isLegal ; i=i+1) {
			if(flights[tour[i]][tour[i+1]] == false) {
				isLegal = false;
			}
		}
		// to check if it is permitted to travel from the last town to the beginning.
		if(flights[tour[tour.length-1]][tour[0]] == false) {
			isLegal = false;
		}
		
		return isLegal;
	}
	
	// task 7
	public static boolean isSolution(boolean[][] flights, int[] tour) {
		boolean is_Solution = true;
		// I used || to check first if tour is null so I can avoid runtime error.
		if(tour == null || tour.length != flights.length) {
			throw new UnsupportedOperationException("Not Implemented yet.");
		}
		// to check if tour is permutation.
		is_Solution = isPermutation(tour);
		
		// to check if the first town in tour is 0
		if(tour[0] != 0) {
			is_Solution = false;
		}
		
		// to check if the steps between the cities are legal.
		boolean isLegal = hasLegalSteps(flights,tour);
		if(is_Solution & !isLegal) {
			is_Solution = false;
		}
		
		return is_Solution;
	}
	
	// task 8
	
	public static boolean evaluateLiteral(int literal, boolean[] assign) {
		boolean literalValue = false;
		
		if(literal > 0) {
			literalValue = assign[literal];
		}else {
			literalValue = !assign[-literal];
		}
		return literalValue;
	}
	
	public static boolean evaluateClause(int[] clause, boolean[] assign) {
		boolean res = false;
		
		for(int i=0 ; i < clause.length & !res ; i=i+1) {
			int literal = clause[i];
			boolean literalValue = evaluateLiteral(literal,assign);
			res = res | literalValue;
		}
		return res;
	}
	
	public static boolean evaluate(int[][] cnf, boolean[] assign) {	
		boolean res = true;
		
		for(int clauseNum = 0 ; clauseNum < cnf.length & res ; clauseNum = clauseNum+1) {
			res = res & evaluateClause(cnf[clauseNum],assign);
		}
		return res;
	}
	
	// task 9
	public static int[][] atLeastOne(int[] lits) {
		int numberOfLits = lits.length;
		int[][] cnf = new int[1][numberOfLits];
		int[] clause = new int[numberOfLits];
		// to make "or" between all the lits in one clause
		for(int i=0 ; i < numberOfLits ; i=i+1) {
			clause[i] = lits[i];
		}
		cnf[0] = clause;
		return cnf;
	}

	// task 10
	public static int[][] atMostOne(int[] lits) {
		int numberOfLits = lits.length;
		int numberOfClauses = numberOfLits*(numberOfLits-1)/2;
		int currentIndex = 0;
		int[][] cnf = new int[numberOfClauses][2];
		
		for(int i=0 ; i < lits.length-1 ; i=i+1) {
			for(int j=i+1 ; j < lits.length ; j=j+1 , currentIndex = currentIndex + 1) {
				int[] clause = {-lits[i], -lits[j]};
				cnf[currentIndex] = clause;
			}
		}
		return cnf;
	}
	
	// task 11
	public static int[][] exactlyOne(int[] lits) {
		int numberOfLits = lits.length;
		int numberOfClauses = numberOfLits*(numberOfLits-1)/2 + 1;
		int currentIndex = 1;
		int[][] cnf = new int[numberOfClauses][];
		
		// at least one in special clause like task 9
		int[] special_clause = new int[numberOfLits];
		for(int i=0 ; i < numberOfLits ; i=i+1) {
			special_clause[i] = lits[i];
		}
		cnf[0] = special_clause;
		
		// at most one for every two lits like task 10.
		for(int i=0 ; i < lits.length-1 ; i=i+1) {
			for(int j=i+1 ; j < lits.length ; j=j+1 , currentIndex = currentIndex + 1) {
				int[] clause = {-lits[i], -lits[j]};
				cnf[currentIndex] = clause;
			}
		}
		return cnf;
	}
	
	/*------------------------
	 *| Part B - tasks 12-20 |
	 * -----------------------*/
	
	// task 12a
	public static int map(int i, int j, int n) {
		int ans = 0;
		ans = n * i + j + 1;
		return ans;
	}
	
	// task 12b
	public static int[] reverseMap(int k, int n) {
		int[] ans = new int[2];
		
		int i = (k-1) / n;
		int j = (k-1) % n;
		
		ans[0] = i;
		ans[1] = j;
		
		return ans;
	}
	
	// task 13
	public static int[][] oneCityInEachStep(int n) {
		// I multiplied number of clauses from task 11 with n, because every city has n different lits.
		int numberOfClauses = n*(n*(n-1)/2 + 1);
		int[][] cnf = new int[numberOfClauses][];
		int currentIndex = 0;
		
		for(int i = 0 ; i < n ; i = i +1) {
			int[] lits = new int[n];
			for(int j = 0 ; j < n ; j = j + 1) {
				lits[j] = map(i,j,n);
			}
			int[][] cnfExactlyOne = exactlyOne(lits);
			
			for(int x = 0 ; x < cnfExactlyOne.length ; x = x + 1) {
				cnf[currentIndex] = cnfExactlyOne[x];
				currentIndex = currentIndex + 1;
			}
		}
		return cnf;
	}

	// task 14
	public static int[][] eachCityIsVisitedOnce(int n) {
		// I multiplied number of clauses from task 11 with n, because every city has n different lits.
		int numOfClauses = n*(n*(n-1)/2 + 1);
		int[][] cnf = new int[numOfClauses][];
		int currentIndex = 0;
		
		for(int j = 0 ; j < n ; j = j +1) {
			int[] lits = new int[n];
			for(int i = 0 ; i < n ; i = i + 1) {
				lits[i] = map(i,j,n);
			}
			int[][] cnfExactlyOne = exactlyOne(lits);
			
			for(int x = 0 ; x < cnfExactlyOne.length ; x = x + 1) {
				cnf[currentIndex] = cnfExactlyOne[x];
				currentIndex = currentIndex + 1;
			}
		}
		return cnf;
	}
	
	// task 15
	public static int[][] fixSourceCity(int n) {
		// I took just the first literal
		int[][] cnf = new int [1][1];
		cnf[0][0] = map(0,0,n);
		return cnf;
	}
	
	// a function to count how much false above the diagonal of matrix
	public static int falseCounterInFlights(boolean [][] flights) {
		int counter = 0;
		for(int i = 0 ; i < flights.length - 1 ; i = i + 1) {
			for(int j = i + 1 ; j < flights.length ; j = j + 1) {
				if(flights[i][j] == false) {
					counter = counter + 1;
				}
			}
		}
		return counter;
	}
	
	// task 16
	public static int[][] noIllegalSteps(boolean[][] flights) {	
		int n = flights.length;
		// falseCounter is how much false above the diagonal of the matrix
		int falseCounter = falseCounterInFlights(flights);
		
		// I multiplied with 2 because the matrix is symmetric
		int numOfClauses = 2 * n * falseCounter;
		int[][] cnf = new int[numOfClauses][];
		int currentIndex = 0;
		for(int j = 0 ; j < flights.length - 1 ; j = j +1) {
			for(int k = j + 1 ; k < flights.length ; k = k + 1) {
				if(flights[j][k] == false) {
					for(int i = 0 ; i < flights.length ; i = i + 1) {
						int[] clause = {-map(i,j,n),-map((i + 1)%n,k,n)};
						cnf[currentIndex] = clause;
						currentIndex = currentIndex + 1;
						
						// reverseClause is for the entries that has false under the diagonal of the matrix
						int[] reverseClause = {-map(i,k,n),-map((i + 1)%n,j,n)};
						cnf[currentIndex] = reverseClause;
						currentIndex = currentIndex + 1;
					}
				}
			}
		}
		return cnf;
	}
	
	// task 17
	public static int[][] encode(boolean[][] flights) {	
		int n = flights.length;
		int falseCounter = falseCounterInFlights(flights);
		// number of clauses from task 13
		int oneCityNumClauses = n*(n*(n-1)/2 + 1);
		// number of clauses from task 14
		int eachCityNumClauses = n*(n*(n-1)/2 + 1);
		// number of clauses from task 15
		int fixSourceCityNumClauses = 1;
		// number of clauses from task 16
		int noIllegalStepsNumClauses = 2 * n * falseCounter;
		int cnfNumClauses = oneCityNumClauses + eachCityNumClauses + fixSourceCityNumClauses + noIllegalStepsNumClauses;
		
		int[][] cnf = new int[cnfNumClauses][];
		int currentIndex = 0;
		
		int[][] oneCityCnf = oneCityInEachStep(n);
		int[][] eachCityCnf = eachCityIsVisitedOnce(n);
		int[][] fixSourceCityCnf = fixSourceCity(n);
		int[][] noIllegalStepsCnf = noIllegalSteps(flights);
		
		for(int i = 0 ; i < oneCityNumClauses ; i = i + 1) {
			cnf[currentIndex] = oneCityCnf[i];
			currentIndex = currentIndex + 1;
		}
		for(int j = 0 ; j < eachCityNumClauses ; j = j + 1) {
			cnf[currentIndex] = eachCityCnf[j];
			currentIndex = currentIndex + 1;
		}
		cnf[currentIndex] = fixSourceCityCnf[0];
		currentIndex = currentIndex + 1;
		for(int k = 0 ; k < noIllegalStepsNumClauses ; k = k + 1) {
			cnf[currentIndex] = noIllegalStepsCnf[k];
			currentIndex = currentIndex + 1;
		}
		return cnf;
	}

	// task 18
	public static int[] decode(boolean[] assignment, int n) {
		if(assignment == null) {
			throw new UnsupportedOperationException("Not Implemented yet.");
		}
		if(assignment.length != n * n + 1) {
			throw new UnsupportedOperationException("Not Implemented yet.");
		}
		int[] tour = new int[n];
		
		for(int i = 0 ; i < n ; i = i + 1) {
			for(int j = 0 ; j < n ; j = j + 1) {
				if(assignment[map(i,j,n)] == true) {
					tour[i] = j;
				}
			}
		}
		return tour;
	}
	
	// task19
	public static int[] solve(boolean[][] flights) {
		if(!isLegalInstance(flights)) {
			throw new UnsupportedOperationException("ILLEGAL FLIGHTS");
		}
		int n = flights.length;
		int nVars = n * n ;
		SATSolver.init(nVars);
		
		int[][] cnf = encode(flights);
		SATSolver.addClauses(cnf);
		
		boolean[] assignment = SATSolver.getSolution();
		
		if (assignment == null) {
			throw new UnsupportedOperationException("TIMEOUT");
		}
		else if (assignment.length == nVars+1) {
			int[] tour = decode(assignment, n);
			if(!isSolution(flights, tour)) {
				throw new UnsupportedOperationException("ILLEGAL SOLUTION");
			}
			return tour;
		}
		else {
			return null;
		}
		
	}
	
	// a function to get the reversed tour that still has the same first index (0)
	public static int[] reverseTour(int[] tour) {
		int[] reverse = new int[tour.length];
		reverse[0] = tour[0];
		int currentIndex = 1;
		for(int i = tour.length - 1 ; i > 0 ; i = i - 1) {
			reverse[currentIndex] = tour[i];
			currentIndex = currentIndex + 1;
		}
		return reverse;
	}
	
	// a function to add 2 clauses to prevent SATSolver to give the same solution
	public static int[][] specialEncode(int[][] cnf, int[] tour){
		int[][] newCnf = new int[cnf.length + 2][];
		int currentIndex = 0;
		for(int i = 0 ; i < cnf.length ; i = i + 1) {
			newCnf[currentIndex] = cnf[i];
			currentIndex = currentIndex + 1;
		}
		
		// clauseA is to prevent a solution that gives tour
		int[] clauseA = new int[tour.length];
		for(int i = 0 ; i < tour.length ; i = i + 1) {
			clauseA[i] = -map(i,tour[i],tour.length);
		}
		
		newCnf[currentIndex] = clauseA;
		currentIndex = currentIndex + 1;
		
		// clauseB is to prevent a solution that gives reversed tour
		int[] clauseB = new int[tour.length];
		int[] reverseTour = reverseTour(tour);
		for(int i = 0 ; i < tour.length ; i = i + 1) {
			clauseB[i] = -map(i,reverseTour[i],tour.length);
		}
		
		newCnf[currentIndex] = clauseB;
		
		return newCnf;
	}
	
	// task20
	public static boolean solve2(boolean[][] flights) {
		if(!isLegalInstance(flights)) {
			throw new UnsupportedOperationException("ILLEGAL FLIGHTS");
		}
		
		
		int[][] cnf = encode(flights);
		int[] firstSolve = solve(flights);
		int[][] specialCnf = specialEncode(cnf, firstSolve);
		
		int n = flights.length;
		int nVars = n * n ;
		SATSolver.init(nVars);
		SATSolver.addClauses(specialCnf);
		
		boolean[] assignment = SATSolver.getSolution();
		
		if (assignment == null) {
			throw new UnsupportedOperationException("TIMEOUT");
		}
		else if (assignment.length == nVars+1) {
			int[] tour = decode(assignment, n);
			if(!isSolution(flights, tour)) {
				return false;
			}
			return true;
		}
		else {
			return false;
		}
	}

}
