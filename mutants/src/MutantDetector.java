
public class MutantDetector {
	public static void main(String args[]){
		String[] dna= {
		 "ATGCGA","CAGTTC","TTATGT","AGTTGG","CFCTCA","CCACTG"
		};
//		String[] dna= {
//				 "ATGCGAFFF","CAGTTCFFF","TTATGTFFF","AGTTGGFFF","CFCTCAFFF","CCACTGFFF"
//				};
		
		MutantDetector  detector= new MutantDetector();
		if(detector.isMutant(dna)){
			System.out.println("THIS IS A MUTANT!!!!!!!");
		}else{
			System.out.println("sorry this is not a mutant -__-");
		}
		
	}
	
	enum Horizontal{
		   IZQUIERDA(-1), CENTRO(0),DERECHA(1);
		   private int valor;
		   Horizontal(int valor){
		      this.valor = valor;
		   }
		   public int getValor(){
		      return this.valor;
		   }
	}
	enum Vertical{
		 	CENTRO(0), ARRIBA(-1);
		   private int valor;
		   Vertical(int valor){
		      this.valor = valor;
		   }
		   public int getValor(){
		      return this.valor;
		   }
	}
	
	public boolean isMutant(String [] dna){
		//primero vemos si se nota a simple vista si es mutante.
		for(String item: dna){
			if(item.startsWith("AAAA")||item.startsWith("TTTT") ||item.startsWith("CCCC") || item.startsWith("GGGG")||
					item.endsWith("AAAA")||item.endsWith("TTTT") ||item.endsWith("CCCC") || item.endsWith("GGGG")){
				System.out.println("This is oviusly a mutant");
				return true;
			}
		}
		char tableTest [][]= new char[dna.length][dna.length];
		for(int i=0; i< dna.length; i++){
			String row = dna[i];
			for(int j = 0 ; j< dna.length; j++){
				tableTest[j][i]= row.charAt(j);
				System.out.println("Start to validate ["+j+"]["+i+"] charBase:"+tableTest[j][i]);
				if(searchArround(tableTest, j, i)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean searchArround(char tableTest [][], int currentH, int currentV ){
		for(Horizontal h: Horizontal.values()){
			for(Vertical v: Vertical.values()){
				if((h!= Horizontal.DERECHA || v != Vertical.CENTRO) && (h!= Horizontal.CENTRO || v != Vertical.CENTRO) ){
					System.out.println("    serach direction "+h+","+v+" -- "+tableTest[currentH][currentV]);
					if(makeValidation(tableTest, currentH, currentV, h, v, 1, tableTest[currentH][currentV])){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean makeValidation(char tableTest [][], int currentH, int currentV, Horizontal horizontalDirection, Vertical verticalDirection, int counter, char baseChar ){
		int nextH = currentH +horizontalDirection.valor;
		int nextV = currentV +verticalDirection.valor;
		
		System.out.println(baseChar +"=="  +"["+nextH+"]["+nextV+"] counter:"+counter);
		if(nextH < 0 || nextV<0 || nextH == tableTest[0].length || nextV == tableTest[0].length){
			System.out.println("is out of tableTest "+nextH+","+nextV);
			return false;
		}
		else if(baseChar == tableTest[nextH][nextV] ){
			
			if(counter==3){
				return true;
			}else{
				System.out.println("makeValidation<<<<<<<<<<"+baseChar);
				return makeValidation(tableTest, nextH, nextV, horizontalDirection, verticalDirection, counter+1, baseChar);
			}
		}
		System.out.println("it is not a mutant");
		return false;
	}
}
