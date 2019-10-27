//
// Created by denis on 26.05.2019.
//
#include <iostream>
#include <vector>
#include "term.hpp"
#include "binary_search_deluxe.hpp"
#include "autocomplete.hpp"

int main() {
    term term1(20, "ariana");
    term term2(10, "ari");
    term term3(3, "arj");
    term term4(4, "a");
//    std::cout << (term1 > term3) << std::endl;
//    std::cout << (term1 > term2) << std::endl;
//    std::cout << term1() << std::endl;
//    std::cout << term2.toString() << std::endl;
    term term5(3, "ria");
//    std::cout << term5() << std::endl;
//    term1 = term2 = term3;
//    std::cout << term1() << std::endl;
//    std::cout << term2() << std::endl;
//    std::cout << term3() << std::endl;
    term term6(9, "rio");
    term term7(8, "riodejaneiro");
//    std::cout << term::compareTerms(term7, term6, term::by_prefix_order(3)) << std::endl;
//    std::cout << term::compareTerms(term7, term6, term::by_prefix_order(4)) << std::endl;
//    std::cout << term::compareTerms(term7, term6, term::by_reverse_weight_order()) << std::endl;
//    std::cout << term::compareTerms(term6, term6, term::by_reverse_weight_order()) << std::endl;
    term arr[]{term1, term2, term3, term4, term5, term6, term7};
    autocomplete aut(arr, sizeof(arr) / sizeof(term));
    std::string test = "a";
    int res_size = aut.number_of_matches(test);
    term* res = aut.all_matches(test);
    std::cout << "res_size: " << res_size << std::endl;
    for(int i = 0; i < res_size; i++){
        std::cout << res[i]() << std::endl;
    }
    test = "ri";
    res_size = aut.number_of_matches(test);
    res = aut.all_matches(test);
    std::cout << "res_size: " << res_size << std::endl;
    for(int i = 0; i < res_size; i++){
        std::cout << res[i]() << std::endl;
    }

}
