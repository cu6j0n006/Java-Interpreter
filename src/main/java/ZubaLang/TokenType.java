package ZubaLang;

enum TokenType {
    // Single-character tokens
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,
    COMMA, DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR,

    // One or two character tokens
    BANG, BANG_EQUAL,
    EQUAL, EQUAL_EQUAL,
    GREATER, GREATER_EQUAL,
    LESS, LESS_EQUAL,

    // Literals
    IDENTIFIER, STRING, NUMBER, BOOLEAN,

    // Keywords
    AND, CLASS, ELSE, FALSE, FUNC, FOR, IF, VAR, NULL, PRINT, OR, SUPER, THIS, TRUE, WHILE,

    // zubaLang-specific keywords
    LET,             // Kirundi-style variable declaration
    FATA,            // "fata" -> function declaration
    SUBIRA,        // "gusubira" -> for loop
    NIMBA,           // "nimba" -> if
    KANDI,           // "kandi" -> else if
    ATARIVYO,       // "bitarivyo" -> else
    ANDIKA,         // "andika" -> print
    SOMA,            // "soma" -> input
    UBUSA,             // "ubusa" -> null / none
    MUGIHE,         // Optional: while loop
    SUBIZA,         // "SUBIZA": return statement
    NIVY0,           // "true" (true)
    SIVYO,        //  "false"

    // End of input
    EOF
}
