package com.nagarro.training.userVerificationApi.service.impl;
import com.nagarro.training.userVerificationApi.model.User;
import com.nagarro.training.userVerificationApi.service.SortUserService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Service implementation for sorting users based on various conditions.
 * @author shreyarathour
 */
@Service
public class SortUserServiceImpl implements SortUserService {
	/**
     * Sorts users based on name length being odd.
     *
     * @param userList List of users to be sorted.
     * @return List of users sorted based on name length being odd.
     */
    @Override
    public List<User> basedOnNameAndOdd(List<User> userList) {
        return sortUsersByCondition(userList, user -> isOddLengthName(user.getName()));
    }
    /**
     * Sorts users based on name length being even.
     *
     * @param userList List of users to be sorted.
     * @return List of users sorted based on name length being even.
     */
    @Override
    public List<User> basedOnNameAndEven(List<User> userList) {
        return sortUsersByCondition(userList, user -> isEvenLengthName(user.getName()));
    }
    /**
     * Sorts users based on age being odd.
     *
     * @param userList List of users to be sorted.
     * @return List of users sorted based on age being odd.
     */
    @Override
    public List<User> basedOnAgeAndOdd(List<User> userList) {
        return sortUsersByCondition(userList, user -> user.getAge() % 2 != 0);
    }
    /**
     * Sorts users based on age being even.
     *
     * @param userList List of users to be sorted.
     * @return List of users sorted based on age being even.
     */
    @Override
    public List<User> basedOnAgeAndEven(List<User> userList) {
        return sortUsersByCondition(userList, user -> user.getAge() % 2 == 0);
    }
    /**
     * Sorts users based on a given condition using a predicate.
     *
     * @param userList  List of users to be sorted.
     * @param condition Predicate defining the sorting condition.
     * @return List of users sorted based on the specified condition.
     */
    private List<User> sortUsersByCondition(List<User> userList, java.util.function.Predicate<User> condition) {
        return userList.stream()
                .filter(condition)
                .sorted(getSortingComparator())
                .collect(Collectors.toList());
    }
    /**
     * Gets the comparator for sorting based on adjusted name length.
     *
     * @return Comparator for sorting users based on adjusted name length.
     */
    private Comparator<User> getSortingComparator() {
        return Comparator.comparingInt(user -> {
            String name = user.getName();
            int whitespaceCount = (int) name.chars().filter(Character::isWhitespace).count();
            return name.length() - whitespaceCount;
        });
    }
    /**
     * Checks if the length of the name is odd.
     *
     * @param name The name to check.
     * @return True if the length is odd, false otherwise.
     */
    private boolean isOddLengthName(String name) {
        int adjustedLength = calculateAdjustedLength(name);
        return adjustedLength % 2 != 0;
    }
    /**
     * Checks if the length of the name is even.
     *
     * @param name The name to check.
     * @return True if the length is even, false otherwise.
     */
    private boolean isEvenLengthName(String name) {
        int adjustedLength = calculateAdjustedLength(name);
        return adjustedLength % 2 == 0;
    }
    /**
     * Calculates the adjusted length of the name by excluding whitespaces.
     *
     * @param name The name for which to calculate the adjusted length.
     * @return The adjusted length of the name.
     */
    private int calculateAdjustedLength(String name) {
        int whitespaceCount = (int) name.chars().filter(Character::isWhitespace).count();
        return name.length() - whitespaceCount;
    }
}

