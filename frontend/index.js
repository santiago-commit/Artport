var API = 'http://localhost:8080'

async function showPosts() {
	const response = await fetch(API + '/posts')
	const posts = await response.json();
	posts.forEach((post) => {
		showPost(post)
	})
}

function showPost(post) {
	const userId = post.user.userId
	const name = post.user.username
	const postId = post.postId
	const postDescription = post.description
	const imageLink = post.files[0]

	const postDiv = document.createElement('div')
	postDiv.className = 'post'

	const title = document.createElement('h2')
	const titleLink = document.createElement('a')

	titleLink.href = './users/' + userId
	titleLink.innerHTML = name
	title.appendChild(titleLink)
	
	const description = document.createElement('p')
	const descriptionLink = document.createElement('a')

	descriptionLink.href = './posts/' + postId
	descriptionLink.innerHTML = postDescription
	description.appendChild(descriptionLink)

	const image = document.createElement('img')
	image.src = imageLink

	postDiv.appendChild(title)
	postDiv.appendChild(description)
	postDiv.appendChild(image)

	const posts = document.getElementById('posts')
	posts.appendChild(postDiv)
}

showPosts()
