package ru.penzgtu.ponamorev.cipherVigener.cipherLogic;

public enum Alphabet {
    A_UP(0, 'A'),
    A_LOW(1, 'a'),
    B_UP(2, 'B'),
    B_LOW(3, 'b'),
    C_UP(4, 'C'),
    C_LOW(5, 'c'),
    D_UP(6, 'D'),
    D_LOW(7, 'd'),
    E_UP(8, 'E'),
    E_LOW(9, 'e'),
    F_UP(10, 'F'),
    F_LOW(11, 'f'),
    G_UP(12, 'G'),
    G_LOW(13, 'g'),
    H_UP(14, 'H'),
    H_LOW(15, 'h'),
    I_UP(16, 'I'),
    I_LOW(17, 'i'),
    J_UP(18, 'J'),
    J_LOW(19, 'j'),
    K_UP(20, 'K'),
    K_LOW(21, 'k'),
    L_UP(22, 'L'),
    L_LOW(23, 'l'),
    M_UP(24, 'M'),
    M_LOW(25, 'm'),
    N_UP(26, 'N'),
    N_LOW(27, 'n'),
    O_UP(28, 'O'),
    O_LOW(29, 'o'),
    P_UP(30, 'P'),
    P_LOW(31, 'p'),
    Q_UP(32, 'Q'),
    Q_LOW(33, 'q'),
    R_UP(34, 'R'),
    R_LOW(35, 'r'),
    S_UP(36, 'S'),
    S_LOW(37, 's'),
    T_UP(38, 'T'),
    T_LOW(39, 't'),
    U_UP(40, 'U'),
    U_LOW(41, 'u'),
    V_UP(42, 'V'),
    V_LOW(43, 'v'),
    W_UP(44, 'W'),
    W_LOW(45, 'w'),
    X_UP(46, 'X'),
    X_LOW(47, 'x'),
    Y_UP(48, 'Y'),
    Y_LOW(49, 'y'),
    Z_UP(50, 'Z'),
    Z_LOW(51, 'z');

    private final int index;
    private final char value;

    Alphabet(int index, char value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return this.index;
    }

    public char getValue() {
        return this.value;
    }
}