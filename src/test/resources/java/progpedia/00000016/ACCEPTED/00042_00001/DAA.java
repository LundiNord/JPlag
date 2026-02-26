import java.util.*;

class DAA{

static void confere(final String s1, final String s2){
boolean print=false;
for(int i=0;i<s1.length();i++){
assert i < s2.length();
if(s1.charAt(i)==s2.charAt(i))continue;
if(s1.charAt(i)!=s2.charAt(i)){
System.out.println(s1.charAt(i)+""+s2.charAt(i));print=true;break;


}

}

if(print==false)System.out.println("Nenhum");



}


public static void main(String[] args){

    Scanner in = new Scanner(System.in);
    String s1=in.nextLine();
    String s2=in.nextLine();
    confere(s1,s2);


}




}