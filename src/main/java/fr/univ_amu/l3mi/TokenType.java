package fr.univ_amu.l3mi;

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
    FATA,            // "fata" → function declaration
    SUBIRA,        // "gusubira" → for loop
    NIMBA,           // "nimba" → if
    KANDI,           // "kandi" → else if
    ATARIVYO,       // "bitarivyo" → else
    ANDIKA,         // "andika" → print
    SOMA,            // "soma" → input
    UBUSA,             // "ubusa" → null / none
    KORA,            // "kora" → do/execute (optional utility)
    ISUBIRA,         // Optional: while-like loop
    SUBIZA,         // "SUBIZA": return statement
    NIVY0,           // Optional: "truth" (true)
    SIVYO,        // Optional: "falsehood" (false)

    // End of input
    EOF
}
