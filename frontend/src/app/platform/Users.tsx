"use client";

import { useState, useEffect } from "react";
import axios from "axios";

type User = {
  id: string;
  username: string;
  email: string;
  role: "admin" | "editor";
};

const Users = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [isSearching, setIsSearching] = useState(false);
  const [users, setUsers] = useState<User[]>([]);
  const [searchedUsers, setSearchedUsers] = useState<User[]>([]);

  const readUsers = async () => {
    try {
      setIsLoading(true);
      const respone = await axios.get(`${process.env.NEXT_PUBLIC_API}user`);
      setUsers(respone.data.data);
    } catch (e) {
      console.log(e);
    } finally {
      setIsLoading(false);
    }
  };

  const searchUser = async (query: string) => {
    if (!query || query.trim() === "") {
      setSearchedUsers([]);
      return;
    }

    try {
      setIsSearching(true);
      const response = await axios.get(
        `${process.env.NEXT_PUBLIC_API}user/search`,
        {
          params: {
            q: query,
          },
        }
      );
      setSearchedUsers(response.data.data);
    } catch (e) {
      console.log(e);
      setSearchedUsers([]);
    } finally {
      setIsSearching(false);
    }
  };

  useEffect(() => {
    readUsers();
  }, []);

  return (
    <div>
      {isLoading ? (
        <div className="text-2xl animate-pulse">loading...</div>
      ) : (
        <div>
          {users.map((user) => (
            <div key={user.id}>
              <div>{user.username}</div>
              <div>{user.email}</div>
              <div>{user.role}</div>
            </div>
          ))}
        </div>
      )}
      <div>
        <div>Search</div>
        <input type="text" onChange={(e) => searchUser(e.target.value)} />
        {isSearching && <div>Searching...</div>}
        {!isSearching &&
          searchedUsers.map((user) => <div key={user.id}>{user.username}</div>)}
      </div>
    </div>
  );
};

export default Users;
