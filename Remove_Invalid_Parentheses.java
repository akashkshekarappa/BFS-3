// Time Complexity : O(2 ^n)
// Space Complexity : O(2 ^n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :no


import java.util.*;

public class Remove_Invalid_Parentheses {
	public List<String> removeInvalidParentheses(String s) {
		//edge case
		if(s == null)
			return new ArrayList<>();

		List<String> result = new ArrayList<>();
		Queue<String> q = new LinkedList<>();   // bfs
		HashSet<String> visited = new HashSet<>();  //equal string can be seen at diff level, so mark as visited to avoid reprocessing
		q.add(s);   // add the input string to queue and mark as visited
		visited.add(s);
		boolean flag = false;   //to track whether a valid string is formed at particular level or not

		while(!q.isEmpty() && !flag){
			int size = q.size();    // to process all nodes in the current level
			for(int i = 0; i<size; i++){
				String current = q.poll();
				if(isValid(current)){   //if current string is valid
					result.add(current);
					flag = true;    // flag = true means valid string is formed at current level
				}

				if(!flag){
					// if flag = false, means no string is valid, add child nodes in to queue to process next level
					// hence we remove 1 parentheses from current string and add to queue if not visited
					for(int j=0; j<current.length(); j++){
						char c = current.charAt(j);
						if(Character.isLetter(c))  //skip if character is letter
							continue;

						else{   //else create a child string without character c and add to queue if not visited
							String child = current.substring(0, j) + current.substring(j + 1);
							if(!visited.contains(child)){
								q.add(child);
								visited.add(child);
							}
						}
					}
				}
			}
		}
		return result;
	}

	private boolean isValid(String current){
		int count = 0;
		for(int i=0; i<current.length(); i++){
			char c = current.charAt(i);
			if(Character.isLetter(c))
				continue;
			else if(c == '(')
				count++;
			else {  // if c == ')'
				if(count > 0)
					count--;
				else
					return false;       
			}
		}
		return count == 0;
	}
}
