/**
 * 22ВП3 в3 Сахно Новосельцев
 */

package com.niksahn.transport;

import java.util.Scanner;

import static com.niksahn.transport.Transport.set_transport_length;
import static com.niksahn.transport.UI.displayInfoMsg;
import static com.niksahn.transport.UI.intaractionStart;


public class Main {

    public static void main(String[] args) {
        displayInfoMsg();
        set_transport_length(10);
        intaractionStart();
    }
}

/* TODO
 Auto a = new Auto();
     a = inputAuto();
*/
