package com.example.proyectoandroidtdp.Filtros;

import android.text.InputFilter;
import android.text.Spanned;

public class CreadorDeFiltros implements CreadorDeFiltrosAbstracto{

    public CreadorDeFiltros(){
    }

    /**
     * Crea y devuelve un InputFilter para numeros fraccionarios en base 2.
     * @return InputFilter para numeros binarios fraccionarios
     */
    public InputFilter getFiltroBinarioFraccionario(){
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                for (int i = start; i < end; i++) {
                    if (source.charAt(i)<'.' || source.charAt(i)>'1') {
                        return "";
                    }
                }
                return null;
            }
        };
    }

    public InputFilter getFiltroBinarioFraccionarioSignado(){
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                for (int i = start; i < end; i++) {
                    if (source.charAt(i)<'-' || source.charAt(i)>'1') {
                        return "";
                    }
                }
                return null;
            }
        };
    }

    /**
     * Crea y devuelve un InputFilter para numeros fraccionarios en base 8.
     * @return InputFilter para numeros octales fraccionarios
     */
    public InputFilter getFiltroOctalFraccionario() {
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                for (int i = start; i < end; i++) {
                    if (source.charAt(i) < '.' || source.charAt(i) > '7') {
                        return "";
                    }
                }
                return null;
            }
        };
    }

    /**
     * Crea y devuelve un InputFilter para numeros fraccionarios en base 10.
     * @return InputFilter para numeros decimales fraccionarios
     */
    public InputFilter getFiltroDecimalFraccionario(){
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                for (int i = start; i < end; i++) {
                    if (source.charAt(i)<'.' || source.charAt(i)>'9') {
                        return "";
                    }
                }
                return null;
            }
        };
    }


    /**
     * Crea y devuelve un InputFilter para numeros fraccionarios en base 16.
     * @return InputFilter para numeros hexadecimales fraccionarios
     */
    public InputFilter getFiltroHexaFraccionario(){
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                source = source.toString().toUpperCase();

                for (int i = start; i < end; i++) {
                    if (!(source.charAt(i)=='.' || Character.isDigit(source.charAt(i)) ||
                            (source.charAt(i)>='A' && source.charAt(i)<='F') ||
                            (source.charAt(i)>='a' && source.charAt(i)<='f'))) {
                        return "";
                    }
                }
                return source;
            }
        };
    }

    /**
     * Crea y devuelve un InputFilter para numeros enteros en base 2.
     * @return InputFilter para numeros enteros binarios.
     */
    public InputFilter getFiltroBinarioEntero(){
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                for (int i = start; i < end; i++) {
                    if (source.charAt(i)<'0' || source.charAt(i)>'1') {
                        return "";
                    }
                }
                return null;
            }
        };
    }

    /**
     * Crea y devuelve un InputFilter para numeros enteros en base 8.
     * @return InputFilter para numeros enteros octales.
     */
    public InputFilter getFiltroOctalEntero() {
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                for (int i = start; i < end; i++) {
                    if (source.charAt(i) < '0' || source.charAt(i) > '7') {
                        return "";
                    }
                }
                return null;
            }
        };
    }

    /**
     * Crea y devuelve un InputFilter para numeros enteros en base 10.
     * @return InputFilter para numeros enteros decimales.
     */
    public InputFilter getFiltroDecimalEntero(){
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                for (int i = start; i < end; i++) {
                    if (source.charAt(i)<'0' || source.charAt(i)>'9') {
                        return "";
                    }
                }
                return null;
            }
        };
    }

    /**
     * Crea y devuelve un InputFilter para numeros enteros en base 16.
     * @return InputFilter para numeros enteros hexadecimales.
     */
    public InputFilter getFiltroHexaEntero(){
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                source = source.toString().toUpperCase();

                for (int i = start; i < end; i++) {
                    if (!(Character.isDigit(source.charAt(i)) ||
                            (source.charAt(i)>='A' && source.charAt(i)<='F') ||
                            (source.charAt(i)>='a' && source.charAt(i)<='f'))) {
                        return "";
                    }
                }
                return source;
            }
        };
    }


}
