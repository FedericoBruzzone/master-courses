# Distribuzione del carico

- Distribuzione quando gli agenti non variano: la funzione deve essere bilanciata $|\delta(\_)^{-1}| \approx \frac{|U|}{|A|}$
- Distribuzione quando gli agenti variano: la funzione deve essere controvariante
$$
    B \supseteq A \quad a \in A \\
    |\delta_B(a)^{-1}| \supseteq |\delta_A(a)^{-1}|

$$
- Permutazioni alleatorie

# Codici istantanei

- Cos'è un codice e ordinamento per prefissi
- Quando il codice è istantaneo
- Quando è completo
- Kraft-McMillan (sequenza eventualmente infinita)

*Codici per gli interi*

- Codice binario $\lfloor \log_2(n) \rfloor + 1$
- Codice binario allineato
- Codice binario ridotto $\lfloor \log_2(n + 1) \rfloor$
- Codice binario minimale $\lceil \log_2(k) \rceil - [n \leq 2^{\lceil \log_2(k) \rceil} - k]$
- Codice unario $n + 1$
- Codice gamma $(2 * \lfloor \log_2(n + 1) \rfloor) - 1$
- Codice delta $(2 * \lfloor \log_2(\lfloor \log_2(n + 1) \rfloor + 1) \rfloor) + 1 + \lfloor \log_2(n + 1) \rfloor$
- Codice di Golomb di modulo k
$$
    k =  \lfloor n / k \rfloor \mod  k \\
    \lfloor n / k \rfloor + 1 + \lceil \log_2(k) \rceil - [n \leq 2^{\lceil \log_2(k) \rceil} - k]
$$
- Codice nibble o byte $(\lfloor \log_2(n) + 1 \rfloor / (k - 1))k$
- PFOR-delta
- Elias-fano

# Gestione della lista di termini

- Minimal perfect hash
- La funzione non permette di riconoscere se un elemento è presente o meno
- Ottimizzazione: utilizzo un array di bit per sapere gli elementi non zero
- Spazio occupato: $1.23n \times rn$, il filtro di Bloom per avere una precisione di $2^{-r}$ deve avere $1.44$ bit

# Risoluzione delle interrogazioni

- Risoluzione in maniera lazy, per l'intersezione sono utili le tabelle di salto
- Heap per l'intersezione. Lineare nell'input con perdita logaritmica.
- Miglioramento: fare avanzare le liste in maniera greedy e al primo disallineamento tornare alla prima lista
- Indici distribuiti, distribuzione lessicale o documentale

# Centralità (punteggi esogeni)

**Geometriche**
- Indegree
- Closeness
- Harmonic
- Betweenness

**Spettrali**
- Autovettore dominante
- Seeley
- Katz
- PageRank

# Punteggi endogeni

- Conteggio delle parole della query in un documento. Facile manipolazione.
- TF-IDF $\frac{tf_{t,d}}{length_d} \log \frac{N}{tf_{t, D}}$
- BM25 $\frac{(k + 1)tf_{t,d}}{k((1 - b) + b \frac{length_d}{avgdl}) + tf_{t,d}} \log \frac{N - n_t + 0.5}{n_t + 0.5}$

