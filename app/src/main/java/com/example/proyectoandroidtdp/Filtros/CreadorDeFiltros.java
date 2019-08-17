package com.example.proyectoandroidtdp.Filtros;

import android.text.InputFilter;
import android.text.Spanned;

public class CreadorDeFiltros implements CreadorDeFiltrosAbstracto{

    public CreadorDeFiltros(){
    }


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
