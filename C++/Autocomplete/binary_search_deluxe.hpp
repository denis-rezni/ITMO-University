//
// Created by denis on 26.05.2019.
//

#ifndef AUTOCOMPLETE_BINARY_SEARCH_DELUXE_HPP
#define AUTOCOMPLETE_BINARY_SEARCH_DELUXE_HPP

#include <iostream>
#include "term.hpp"

class binary_search_deluxe {
public:
    binary_search_deluxe() = delete;

    template<class Compare>
    static int last_index_of(std::vector<term> a, term key, Compare comp){
        int l = -1;
        int r = a.size();
        int m;
        while (l + 1 < r) {
            m = (l + r) / 2;
            if (comp(a[m], key) || (!comp(a[m], key) && !comp(key, a[m]))) {
                l = m;
            } else {
                r = m;
            }
        }
        return l;
    };

    template<class Compare>
    static int first_index_of(std::vector<term> a, term key, Compare comp){
        int l = -1;
        int r = a.size();
        int m;
        while (l + 1 < r) {
            m = (l + r) / 2;
            if (comp(a[m], key)) {
                l = m;
            } else {
                r = m;
            }
        }
        return r;
    };
};


#endif //AUTOCOMPLETE_BINARY_SEARCH_DELUXE_HPP
