#include <utility>

//
// Created by denis on 29.05.2019.
//

#include "autocomplete.hpp"
#include "binary_search_deluxe.hpp"
#include <algorithm>
#include <iostream>
#include <vector>

autocomplete::autocomplete(term* arr, int size) {
    std::copy(arr, arr + size, std::back_inserter(terms));
    std::sort(terms.begin(), terms.end());
}

term *autocomplete::all_matches(const std::string &prefix) const {
    term to_find = term(0, prefix);
    int first = binary_search_deluxe::first_index_of(terms, to_find, term::by_prefix_order(prefix.size()));
    int last = binary_search_deluxe::last_index_of(terms, to_find, term::by_prefix_order(prefix.size()));
    std::vector<term> result;
    for(int i = first; i < last + 1; i++){
        result.push_back(terms[i]);
    }
    std::sort(result.begin(), result.end(), term::by_reverse_weight_order());
    return result.data();
}

int autocomplete::number_of_matches(const std::string &prefix) const {
    term to_find = term(0, prefix);
    int first = binary_search_deluxe::first_index_of(terms, to_find, term::by_prefix_order(prefix.size()));
    int last = binary_search_deluxe::last_index_of(terms, to_find, term::by_prefix_order(prefix.size()));
    return last - first + 1;
}


