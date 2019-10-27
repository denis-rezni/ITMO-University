//
// Created by denis on 29.05.2019.
//

#ifndef AUTOCOMPLETE_AUTOCOMPLETE_HPP
#define AUTOCOMPLETE_AUTOCOMPLETE_HPP

#include "term.hpp"
#include <vector>
class autocomplete {
private:
    std::vector<term> terms;
public:
    autocomplete(term arr[], int array_size);
    term* all_matches(const std::string &prefix) const;
    int number_of_matches(const std::string& prefix) const;

};


#endif //AUTOCOMPLETE_AUTOCOMPLETE_HPP
