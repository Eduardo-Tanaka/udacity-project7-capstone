package br.com.eduardotanaka.knowdown.model;

import android.content.Context;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.eduardotanaka.knowdown.util.LibraryClass;

@IgnoreExtraProperties
public class User implements Comparable<User> {

    public static String TOKEN = "br.com.eduardo.firebase.model.User.TOKEN";
    public static String ID = "br.com.eduardo.firebase.model.User.ID";

    private String id;
    private String name;
    private String gender;
    private String email;
    @Exclude
    private String password;
    @Exclude
    private String newPassword;
    private int score;
    private int acerto;
    private int erro;
    private int coin;

    private List<GuardaRoupa> guardaroupa;
    private int camisa;
    private int calca;
    private int sapato;


    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void saveIdSP(Context context, String token ){
        LibraryClass.saveSP( context, ID, token );
    }

    public void retrieveIdSP(Context context ){
        this.id = LibraryClass.getSP( context, ID );
    }

    public boolean isSocialNetworkLogged( Context context ){
        String token = getTokenSP( context );
        return( token.contains("facebook") || token.contains("google") || token.contains("twitter") );
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setNameInMap( Map<String, Object> map ) {
        if( getName() != null ){
            map.put( "name", getName() );
        }
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private void setGenderInMap( Map<String, Object> map ) {
        if( getGender() != null ){
            map.put( "gender", getGender() );
        }
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private void setEmailInMap( Map<String, Object> map ) {
        if( getEmail() != null ){
            map.put( "email", getEmail() );
        }
    }

    @Exclude
    public String getPassword() {
        return password;
    }
    @Exclude
    public void setPassword(String password) {
        this.password = password;
    }

    @Exclude
    public String getNewPassword() {
        return newPassword;
    }
    @Exclude
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private void setScoreInMap( Map<String, Object> map ) {
        if( String.valueOf(getScore()) != null ){
            map.put( "score", getScore() );
        }
    }


    public int getAcerto() {
        return acerto;
    }

    public void setAcerto(int acerto) {
        this.acerto = acerto;
    }

    private void setAcertoInMap( Map<String, Object> map ) {
        if( String.valueOf(getAcerto()) != null ){
            map.put( "acerto", getAcerto() );
        }
    }


    public int getErro() {
        return erro;
    }

    public void setErro(int erro) {
        this.erro = erro;
    }

    private void setErroInMap( Map<String, Object> map ) {
        if( String.valueOf(getErro()) != null ){
            map.put( "erro", getErro() );
        }
    }


    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    private void setCoinInMap( Map<String, Object> map ) {
        if( String.valueOf(getCoin()) != null ){
            map.put( "coin", getCoin() );
        }
    }


    public List<GuardaRoupa> getGuardaroupa() {
        return guardaroupa;
    }

    public void setGuardaroupa(List<GuardaRoupa> guardaroupa) {
        this.guardaroupa = guardaroupa;
    }

    private void setGuardaRoupaInMap(Map<String, Object> map ) {
        map.put( "guardaroupa", getGuardaroupa() );
    }


    public int getCamisa() {
        return camisa;
    }

    public void setCamisa(int camisa) {
        this.camisa = camisa;
    }

    private void setCamisaInMap( Map<String, Object> map ) {
        if( getCamisa() != 0 ){
            map.put( "camisa", getCamisa() );
        }
    }

    public int getCalca() {
        return calca;
    }

    public void setCalca(int calca) {
        this.calca = calca;
    }

    private void setCalcaInMap( Map<String, Object> map ) {
        if( getCalca() != 0 ){
            map.put( "calca", getCalca() );
        }
    }

    public int getSapato() {
        return sapato;
    }

    public void setSapato(int sapato) {
        this.sapato = sapato;
    }

    private void setSapatoInMap( Map<String, Object> map ) {
        if( getSapato() != 0 ){
            map.put( "sapato", getSapato() );
        }
    }

    public void saveTokenSP(Context context, String token ){
        LibraryClass.saveSP( context, TOKEN, token );
    }
    public String getTokenSP(Context context ){
        return( LibraryClass.getSP( context, TOKEN ) );
    }


    public void saveDB( DatabaseReference.CompletionListener... completionListener ){
        DatabaseReference firebase = LibraryClass.getFirebase().child("users").child( getId() );

        if( completionListener.length == 0 ){
            firebase.setValue(this);
        }
        else{
            firebase.setValue(this, completionListener[0]);
        }
    }

    public void updateDB( DatabaseReference.CompletionListener... completionListener ){

        DatabaseReference firebase = LibraryClass.getFirebase().child("users").child( getId() );

        Map<String, Object> map = new HashMap<>();
        setNameInMap(map);
        setEmailInMap(map);
        setGenderInMap(map);
        setScoreInMap(map);
        setAcertoInMap(map);
        setErroInMap(map);
        setCoinInMap(map);
        setCamisaInMap(map);
        setCalcaInMap(map);
        setSapatoInMap(map);

        if( map.isEmpty() ){
            return;
        }

        if( completionListener.length > 0 ){
            firebase.updateChildren(map, completionListener[0]);
        }
        else{
            firebase.updateChildren(map);
        }
    }

    public void removeDB(){
        DatabaseReference firebase = LibraryClass.getFirebase().child("users").child( getId() );
        firebase.setValue(null);
    }

    public void contextDataDB( Context context ){
        retrieveIdSP( context );
        DatabaseReference firebase = LibraryClass.getFirebase().child("users").child( getId() );

        firebase.addListenerForSingleValueEvent( (ValueEventListener) context );
    }

    @Override
    public int compareTo(User o) {
        if (this.score > o.getScore()) {
            return -1;
        }
        if (this.score < o.getScore()) {
            return 1;
        }
        return 0;
    }
}
