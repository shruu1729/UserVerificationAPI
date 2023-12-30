package com.nagarro.training.userVerificationApi.service.impl;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.training.userVerificationApi.customExceptions.ValidationException;
import com.nagarro.training.userVerificationApi.model.*;
import com.nagarro.training.userVerificationApi.repository.UserRepository;
import com.nagarro.training.userVerificationApi.service.UserDataRetrievalService;
import com.nagarro.training.userVerificationApi.service.SortUserService;
/**
 * Implementation of the UserDataRetrievalService interface that retrieves
 * paginated and sorted user data based on provided parameters.
 *
 * This service interacts with the UserRepository for accessing user data and
 * uses a SortUserService for sorting the data.
 *
 * @author shreyarathour
 */
@Service
public class UserDataRetrievalServiceImpl implements UserDataRetrievalService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SortUserService sortUserService;
    /**
     * Retrieves user data based on sorting, pagination, and limit parameters.
     *
     * @param sortType  The type of sorting (e.g., "Name" or "Age").
     * @param sortOrder The order of sorting (e.g., "Odd" or "Even").
     * @param limit     The maximum number of users to retrieve.
     * @param offset    The offset for pagination.
     * @return UserDataResponse containing sorted and paginated user data.
     * @throws ValidationException If there is an error in fetching and sorting user data.
     */
    @Override
    public UserDataResponse retrieveUserData(String sortType, String sortOrder, int limit, int offset) {
        try {
            List<User> allUsers = this.userRepository.findAll();

            // Check for valid offset and limit
            validateOffsetAndLimit(offset, limit, allUsers.size());

            // Apply pagination
            int startIndex = offset;
            int endIndex = Math.min(offset + limit, allUsers.size());
            List<User> sortedUserList = sortUsers(sortType, sortOrder, allUsers);
            List<User> usersSubset = sortedUserList.subList(startIndex, endIndex);
         // Construct PageInfo
            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setHasNext(endIndex < allUsers.size());
            paginationInfo.setHasPrevious(startIndex > 0);
            paginationInfo.setTotal(allUsers.size());

            // Construct UserDataResponse
            return new UserDataResponse(usersSubset, paginationInfo);

            
        } catch (Exception e) {
            // Wrap and rethrow as ValidationException
            throw new ValidationException("Error in fetching and sorting user data: " + e.getMessage());
        }
    }
    /**
     * Sorts the users based on the provided sortType and sortOrder.
     *
     * @param sortType  The type of sorting (e.g., "Name" or "Age").
     * @param sortOrder The order of sorting (e.g., "Odd" or "Even").
     * @param users     The list of users to be sorted.
     * @return A list of sorted users.
     */
	private List<User> sortUsers(String sortType, String sortOrder,List<User> users){
		
		List<User> allUsers = this.userRepository.findAll();
		
		List<User> sortedUserList = new ArrayList<>();
		
		if("Name".equalsIgnoreCase(sortType) && "Odd".equalsIgnoreCase(sortOrder)) {
			sortedUserList.addAll(this.sortUserService.basedOnNameAndOdd(allUsers));
			sortedUserList.addAll(this.sortUserService.basedOnNameAndEven(allUsers));
		}
		
		else if("Name".equalsIgnoreCase(sortType) && "Even".equalsIgnoreCase(sortOrder)) {
			sortedUserList.addAll(this.sortUserService.basedOnNameAndEven(allUsers));
			sortedUserList.addAll(this.sortUserService.basedOnNameAndOdd(allUsers));
		}
		
		else if("Age".equalsIgnoreCase(sortType) && "Odd".equalsIgnoreCase(sortOrder)) {
			sortedUserList.addAll(this.sortUserService.basedOnAgeAndOdd(allUsers));
			sortedUserList.addAll(this.sortUserService.basedOnAgeAndEven(allUsers));
		}
		
		else if("Age".equalsIgnoreCase(sortType) && "Even".equalsIgnoreCase(sortOrder)) {
			sortedUserList.addAll(this.sortUserService.basedOnAgeAndEven(allUsers));
			sortedUserList.addAll(this.sortUserService.basedOnAgeAndOdd(allUsers));
		}
		
		return sortedUserList;
    }
	/**
     * Helper method to validate offset and limit.
     *
     * @param offset     The offset for pagination.
     * @param limit      The maximum number of users to retrieve.
     * @param totalUsers The total number of users.
     * @throws ValidationException If offset or limit is invalid.
     */
   
    private void validateOffsetAndLimit(int offset, int limit, int totalUsers) {
        if (offset < 0 || offset >= totalUsers) {
            throw new ValidationException("Invalid offset value");
        }
        if (limit < 1 || limit > 5) {
            throw new ValidationException("Limit should be an integer between 1 and 5");
        }
    }
}
