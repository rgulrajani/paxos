#!flask/bin/python
from flask import Flask, jsonify, abort

import hashlib

app = Flask(__name__)

hash_dict = dict()

@app.route('/')
def index():
    return "Welcome to Paxos Interview Challenge 1!"

@app.route('/messages/<string:messg>', methods=['POST'])
def create_hash(messg):
	hash_object = hashlib.sha256(messg)
	hex_dig = hash_object.hexdigest()
	hash_dict[hex_dig] = messg
	return jsonify({'digest': hex_dig})

@app.route('/messages/<string:hex_dig>', methods=['GET'])
def get_message(hex_dig):
	if not hex_dig in hash_dict:
		abort(404)
	return jsonify({'message': hash_dict[hex_dig]})


if __name__ == '__main__':
    app.run(debug=True)
