# Overview of Freivalds' Algorithm
Freivalds' algorithm is a probabilistic algorithm used to verify whether the product of two matrices 
A and 
B equals a third matrix 
C. This algorithm leverages random vectors to perform the verification efficiently compared to the classical deterministic approach.

# Comparison with the Classical Algorithm
### Classical Algorithm: 

The classical method for verifying if 𝐴×𝐵=𝐶
A×B=C has a time complexity of 
𝑂
(
𝑛
3
)
O(n 
3
 ), where 

n is the size of the matrices.
### Freivalds' Algorithm: Freivalds' algorithm, 
on the other hand, has a time complexity of 
𝑂
(
𝑘
𝑛
2
)


O(kn 
2
 ), where 

k is the number of random tests performed.

### Exemple : n=20 k=10 
Freivalds' algorithm: C is the product of A and B? Yes
Time taken by Freivalds' algorithm: 1211800 nanoseconds

Classic verification: C is the product of A and B? Yes
Time taken by classic verification: 1683600 nanoseconds

## Performance with Large Matrices
As the size of the matrices (n*n) increases, Freivalds' algorithm becomes significantly faster in terms of performance compared to the classical method. For large matrices, the 
O(kn 
2
) complexity of Freivalds' algorithm outperforms the 
O(n 
3
 ) complexity of the classical method, making Freivalds' algorithm more suitable for verifying large matrix products.

# Reducing the Probability of Error
Freivalds' algorithm is probabilistic, meaning it may accept an incorrect matrix product with a certain probability. However, this probability can be reduced exponentially by increasing the number of random tests (
k):
Error Probability: Each test has an error probability of at most 
1/2.
Increasing 
k: By performing 
k random tests, the error probability decreases to 
(1/2)^k .Therefore, increasing k reduces the likelihood of error, enhancing the reliability of the verification.
